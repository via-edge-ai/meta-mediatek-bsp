# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

include tf-a-common.inc

SRC_URI = " \
	git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware-private.git;name=tf-a;branch=mt8516-full;protocol=ssh \
	git://github.com/ARMmbed/mbedtls.git;name=mbedtls;destsuffix=mbedtls;protocol=git \
"

SRCREV_tf-a = "b916281b5c020dadb9ab30bffad0e250ff5d35ef"
SRCREV_mbedtls = "mbedtls-2.12.0"

PV_tf-a="2.1+git${SRCPV}"

SRC_URI += "						\
	file://key.ini			\
	file://gfh_conf.ini			\
	file://mtk-pbp-tools		\
	file://dev-info-hdr-tool.py	\
	file://root_prvk.pem		\
	file://rot_key.pem \
"

DEFAULT_ROT_KEY = "${WORKDIR}/rot_key.pem"
SECURE_BOOT_ROT_KEY ?= "${DEFAULT_ROT_KEY}"

TFA_SECURE_BOOT_OPTION = " \
	TRUSTED_BOARD_BOOT=1 \
	GENERATE_COT=1 \
	MBEDTLS_DIR=${WORKDIR}/mbedtls \
	ROT_KEY=${SECURE_BOOT_ROT_KEY} \
"

EXTRA_OEMAKE += " \
	SPD=opteed \
	${@bb.utils.contains("DISTRO_FEATURES", "secure-boot", "${TFA_SECURE_BOOT_OPTION}", "", d)} \
"

do_compile() {
	bbwarn "Building private TF-A. Please do NOT distribute binaries"

	if [ "x${DEFAULT_ROT_KEY}" = "x${SECURE_BOOT_ROT_KEY}" ]; then
		bbwarn "SECURE_BOOT_ROT_KEY is not defined in local.conf, using development key for secure boot"
	fi

	bbnote "Key used for secure boot: ${SECURE_BOOT_ROT_KEY}"
	oe_runmake -C ${S} bl2 bl31 fip
}

do_gen_bl2_img() {
	cp ${B}/${TFA_PLAT}/release/bl2.bin ${B}/bl2.img
	python ${WORKDIR}/mtk-pbp-tools/pbp.py -g ${WORKDIR}/gfh_conf.ini \
		   -i ${WORKDIR}/key.ini -func sign \
		   -o ${B}/bl2.img ${B}/bl2.img
	python ${WORKDIR}/dev-info-hdr-tool.py emmc ${B}/bl2.img \
												${B}/bl2.img
}

do_install_append() {
	install -m 0644 ${B}/bl2.img ${DEPLOY_DIR_IMAGE}/
}

addtask do_gen_bl2_img before do_install after do_compile
