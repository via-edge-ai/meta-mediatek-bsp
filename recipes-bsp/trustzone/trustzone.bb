# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Trustzone image for MediaTek MT8xxx on Pumpkin board"
LICENSE = "CLOSED"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/mtk-tz-prebuilt.git;protocol=ssh"
SRCREV = "df4ea2e86cfcfcde069061074928fb5db085c390"

S="${WORKDIR}/git"

do_install() {
	install -m 0644 ${S}/${MEDIATEK_SOC}/tz.img ${DEPLOY_DIR_IMAGE}/
}
