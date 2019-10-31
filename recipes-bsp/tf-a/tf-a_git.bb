# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

include tf-a-common.inc

inherit distro_features_check
# optee is required because the bl2 binary requires it, and if we don't
# provide it, we won't be able to boot
REQUIRED_DISTRO_FEATURES_pumpkin = "optee"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware.git;branch=mtk-v2.2;protocol=ssh"

SRCREV = "74fe2c94c11100efbf4b9a5566f0f0310ac976dc"
PV="2.2+git${SRCPV}"

do_compile() {
	oe_runmake -C ${S} bl31 fip
}

do_deploy_append() {
	install -m 0644 ${S}/prebuilt/${TFA_PLAT}/release/bl2.img ${DEPLOYDIR}/
}
addtask do_deploy after do_install
