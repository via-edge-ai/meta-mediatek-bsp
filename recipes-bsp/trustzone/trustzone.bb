# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Trustzone image for Pumpkin"
LICENSE = "CLOSED"

SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/mtk-tz-prebuilt.git;protocol=ssh"
SRCREV = "29334382c1cc88764fa6450ba407bdd700c94054"

S="${WORKDIR}/git"

do_install() {
	install -m 0644 ${S}/tz.img ${DEPLOY_DIR_IMAGE}/
}
