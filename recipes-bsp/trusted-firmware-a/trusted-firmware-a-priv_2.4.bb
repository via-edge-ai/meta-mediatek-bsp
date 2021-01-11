require recipes-bsp/trusted-firmware-a/trusted-firmware-a.inc
require trusted-firmware-a-mtk.inc

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware-private.git;name=tfa;branch=mtk-v2.4-full;protocol=ssh"
SRCREV_tfa = "bcabcb59bec430a385a884425657279b10f5b9b9"

SRC_URI += "file://rot_key.pem"

LIC_FILES_CHKSUM += "file://docs/license.rst;md5=189505435dbcdcc8caa63c46fe93fa89"

# mbed TLS v2.24.0
SRC_URI_MBEDTLS = "git://github.com/ARMmbed/mbedtls.git;name=mbedtls;protocol=https;destsuffix=git/mbedtls;branch=master"
SRCREV_mbedtls = "mbedtls-2.24.0"

LIC_FILES_CHKSUM_MBEDTLS = "file://mbedtls/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"


TFA_BUILD_TARGET = "bl2 bl31 fip"

do_gen_bl2_img() {
	cp ${B}/${TFA_PLATFORM}/release/bl2.bin ${B}/bl2.img.tmp
	truncate -s%4 ${B}/bl2.img.tmp
	uboot-mkimage -T mtk_image -a 0x201000 -e 0x201000 -n "media=emmc;aarch64=1" \
			-d ${B}/bl2.img.tmp ${B}/bl2.img
}

do_deploy_append() {
	install -m 0644 ${B}/bl2.img ${DEPLOYDIR}/
}

addtask do_gen_bl2_img before do_install after do_compile

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

DEFAULT_ROT_KEY = "${WORKDIR}/rot_key.pem"
SECURE_BOOT_ROT_KEY ?= "${DEFAULT_ROT_KEY}"

do_compile_prepend() {
	bbnote "Building private TF-A"

	if [ "x${DEFAULT_ROT_KEY}" = "x${SECURE_BOOT_ROT_KEY}" ]; then
		bbwarn "SECURE_BOOT_ROT_KEY is not defined in local.conf, using development key for secure boot"
	fi

	bbnote "Key used for secure boot: ${SECURE_BOOT_ROT_KEY}"
}
