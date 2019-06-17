# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

include tf-a-common.inc

inherit distro_features_check
# optee is required because the bl2 binary requires it, and if we don't
# provide it, we won't be able to boot
REQUIRED_DISTRO_FEATURES = "optee"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware.git;branch=mt8516-bl31;protocol=ssh"

SRCREV = "5d025ad3f559e76781aad0d6735faec43fbf4c01"
PV="2.1+git${SRCPV}"

do_compile() {
	oe_runmake -C ${S} bl31 fip
}

inherit deploy

do_deploy() {
	install -m 0644 ${S}/prebuilt/${TFA_PLAT}/release/bl2.img ${DEPLOY_DIR_IMAGE}/
}
addtask do_deploy after do_install
