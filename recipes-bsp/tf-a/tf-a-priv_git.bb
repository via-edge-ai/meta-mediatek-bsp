# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

include tf-a-common.inc

DEPENDS_append = " coreutils-native u-boot-tools-native "

SRC_URI = " \
	git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware-private.git;name=tf-a;branch=mtk-v2.3-full;protocol=ssh \
	git://github.com/ARMmbed/mbedtls.git;name=mbedtls;destsuffix=mbedtls;nobranch=1 \
"

SRCREV_tf-a = "9c37e9b2db63a0581840e3343fd17689b7ff0414"
SRCREV_mbedtls = "mbedtls-2.18.0"

PV_tf-a="2.3+git${SRCPV}"

SRC_URI += "						\
	file://rot_key.pem \
"

DEFAULT_ROT_KEY = "${WORKDIR}/rot_key.pem"
SECURE_BOOT_ROT_KEY ?= "${DEFAULT_ROT_KEY}"

CFLAGS_append = ' \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "-DNEED_BL32", "", d)} \
	-Wno-error=unused-but-set-variable \
	-Wno-error=implicit-function-declaration \
	-Wno-error=int-conversion \
	-Wno-error=discarded-qualifiers \
	-DBOARD_${TFA_BOARD_NAME} \
'

CFLAGS_append_mt8183 = " \
	-Wno-error=unused-const-variable \
	-Wno-error=unused-value \
	-Wno-error=int-to-pointer-cast \
	-Wno-error=return-type \
	-Wno-error=pointer-sign \
	-Wno-error=parentheses \
	-Wno-error=comment \
"

TFA_SECURE_BOOT_OPTION = " \
	TRUSTED_BOARD_BOOT=1 \
	GENERATE_COT=1 \
	MBEDTLS_DIR=${WORKDIR}/mbedtls \
	ROT_KEY=${SECURE_BOOT_ROT_KEY} \
"

EXTRA_OEMAKE += " \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "SPD=opteed", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "${TFA_SECURE_BOOT_OPTION}", "", d)} \
"

do_compile() {
	bbnote "Building private TF-A"

	if [ "x${DEFAULT_ROT_KEY}" = "x${SECURE_BOOT_ROT_KEY}" ]; then
		bbwarn "SECURE_BOOT_ROT_KEY is not defined in local.conf, using development key for secure boot"
	fi

	bbnote "Key used for secure boot: ${SECURE_BOOT_ROT_KEY}"
	oe_runmake -C ${S} bl2 bl31 fip
}

do_gen_bl2_img() {
	cp ${B}/${TFA_PLAT}/release/bl2.bin ${B}/bl2.img.tmp
	truncate -s%4 ${B}/bl2.img.tmp
	uboot-mkimage -T mtk_image -a 0x201000 -e 0x201000 -n "media=emmc;aarch64=1" \
			-d ${B}/bl2.img.tmp ${B}/bl2.img
}

do_deploy_append() {
	install -m 0644 ${B}/bl2.img ${DEPLOYDIR}/
}

addtask do_gen_bl2_img before do_install after do_compile
