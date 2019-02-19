# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

include tf-a-common.inc

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware.git;branch=mt8516-bl31;protocol=ssh"

SRCREV = "4a9ad79fdc3bab82b0751618b7e89b540fa3c9af"
PV="2.0+git${SRCPV}"

do_compile() {
	oe_runmake -C ${S} bl31 fip
}

do_install_append() {
	install -m 0644 ${S}/prebuilt/${TFA_PLAT}/release/bl2.img ${DEPLOY_DIR_IMAGE}/
}
