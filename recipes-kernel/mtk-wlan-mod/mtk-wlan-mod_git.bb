# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Mediatek wlan driver"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=90675d4b2a0ffc46ef9ff36dfe4063fe"

inherit module

SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/mtk-wlan-mod.git;protocol=ssh;branch=v4.19"
SRCREV = "bf5c9971dd0cf3374e315f7f615777751d274cfd"

S = "${WORKDIR}/git"

EXTRA_OEMAKE+=" \
	MTK_COMBO_CHIP=MT6632 \
	CONFIG_MTK_WIFI_ONLY=m \
	CONFIG_MTK_COMBO=m \
	CONFIG_MTK_COMBO_WIFI=m \
	CONFIG_MTK_COMBO_WIFI_HIF=sdio \
	WLAN_CHIP_ID=mt7668 \
"

RPROVIDES_${PN} += "kernel-module-mtk-wlan"
