# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

include tf-a-common.inc

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware.git;branch=mt8516-bl31;protocol=ssh"

SRCREV = "3b6d923fc7ba6c1149de18bef5b2965a219cc509"
PV="2.0+git${SRCPV}"

do_compile() {
	oe_runmake -C ${S} bl31 fip
}

inherit deploy

do_deploy() {
	install -m 0644 ${S}/prebuilt/${TFA_PLAT}/release/bl2.img ${DEPLOY_DIR_IMAGE}/
}
addtask do_deploy after do_install
