require recipes-bsp/trusted-firmware-a/trusted-firmware-a.inc

inherit deploy

DEPENDS = "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "libdram", "libdram-prebuilt", d)}"
DEPENDS:append = " libdevinfo-prebuilt"
DEPENDS:append = " libbase-prebuilt"

COMPATIBLE_MACHINE = "mt*"

# Use TF-A for version
SRCREV_FORMAT = "tfa"

TFA_UBOOT = "1"

DEPENDS:append = " coreutils-native u-boot-tools-native"

EXTRA_OEMAKE += "E=0"
EXTRA_OEMAKE += "NEED_BL33=yes"
EXTRA_OEMAKE += "LIBDRAM=${STAGING_DIR_TARGET}/${libdir}/libdram.a"
EXTRA_OEMAKE += "LIBBASE=${STAGING_DIR_TARGET}/${libdir}/libbase.a"
EXTRA_OEMAKE += "LIBDEVINFO=${STAGING_DIR_TARGET}/${libdir}/libdevinfo.a"

EXTRA_OEMAKE:append = " \
	${@bb.utils.contains("MACHINE_FEATURES", "ufs-boot", "STORAGE_UFS=1", "", d)} \
"

# OP-TEE
DEPENDS:append = " ${@bb.utils.contains("DISTRO_FEATURES", "optee", "optee-os", "", d)}"
TFA_SPD = "${@bb.utils.contains("DISTRO_FEATURES", "optee", "opteed", "", d)}"
EXTRA_OEMAKE:append = " \
	BL32=${STAGING_DIR_TARGET}/${nonarch_base_libdir}/firmware/tee.bin \
	NEED_BL32=${@bb.utils.contains("DISTRO_FEATURES", "optee", "yes", "no", d)} \
	CFLAGS+=${@bb.utils.contains("DISTRO_FEATURES", "optee", "-DNEED_BL32", "", d)} \
"

S = "${WORKDIR}/git"

SRC_URI:remove = " \
    file://0002-pmf.h-made-PMF_STOTE_ENABLE-pass-Wtautological.patch  \
    file://0003-xlat-tables-v2-remove-tautological-assert.patch \
"

SRC_URI += "file://rot_key.pem"

LIC_FILES_CHKSUM += "file://docs/license.rst;md5=b2c740efedc159745b9b31f88ff03dde"

SRC_URI_MBEDTLS = "git://github.com/ARMmbed/mbedtls.git;name=mbedtls;protocol=https;destsuffix=git/mbedtls;branch=master"
SRCREV_mbedtls = "mbedtls-2.26.0"

LIC_FILES_CHKSUM_MBEDTLS = "file://mbedtls/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

TFA_BUILD_TARGET = "${@bb.utils.contains('TFA_USE_PREBUILT_BL2', 'yes', '', 'bl2', d)} bl31 fip"

do_gen_bl2_img() {
	media="emmc"
	if [ "${@bb.utils.contains('MACHINE_FEATURES', 'ufs-boot', 'ufs-boot', '', d)}" = "ufs-boot" ]; then
		media="ufs"
	fi

	cp ${B}/${TFA_PLATFORM}/release/bl2.bin ${B}/bl2.img.tmp
	truncate -s%4 ${B}/bl2.img.tmp
	uboot-mkimage -T mtk_image -a 0x201000 -e 0x201000 -n "media=$media;arm64=1" \
			-d ${B}/bl2.img.tmp ${B}/bl2.img
}

do_deploy:append() {
	if [ "x${TFA_USE_PREBUILT_BL2}" = "xyes" ]; then
		install -m 0644 ${S}/prebuilt/${TFA_BOARD_NAME}/release/bl2.img ${DEPLOYDIR}/
	else
		install -m 0644 ${B}/bl2.img ${DEPLOYDIR}/
	fi
}

python() {
    if d.getVar('TFA_USE_PREBUILT_BL2') != 'yes':
        bb.build.addtask('do_gen_bl2_img', 'do_install', 'do_compile', d)
}

#
# Secure Boot
#
TFA_SECURE_BOOT_OPTION = " \
	TRUSTED_BOARD_BOOT=1 \
	GENERATE_COT=1 \
	ROT_KEY=${SECURE_BOOT_ROT_KEY} \
"

EXTRA_OEMAKE += " \
	${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "${TFA_SECURE_BOOT_OPTION}", "", d)} \
"

TFA_MBEDTLS = " \
	${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "1", "", d)} \
"

DEFAULT_ROT_KEY = "${WORKDIR}/rot_key.pem"
SECURE_BOOT_ROT_KEY ?= "${DEFAULT_ROT_KEY}"

do_compile:prepend() {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'secure-boot', 'secure-boot', '', d)}" = "secure-boot" ]; then
		if [ "x${DEFAULT_ROT_KEY}" = "x${SECURE_BOOT_ROT_KEY}" ]; then
			bbwarn "SECURE_BOOT_ROT_KEY is not defined in local.conf, using development key for secure boot"
		fi

		bbnote "Key used for secure boot: ${SECURE_BOOT_ROT_KEY}"
	fi
}
