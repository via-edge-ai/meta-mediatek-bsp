require recipes-bsp/trusted-firmware-a/trusted-firmware-a_${PV}.bb
require trusted-firmware-a-mtk.inc

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware-private.git;name=tfa;branch=mtk-v2.3-full;protocol=ssh"
SRCREV_tfa = "c1984dc174faaff21a7599d954c01cef97710229"

SRC_URI += "file://rot_key.pem"

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
