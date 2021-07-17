# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MT7668 Wi-Fi driver"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=90675d4b2a0ffc46ef9ff36dfe4063fe"

inherit module

SRC_URI = "${AIOT_NDA_URI}/mt7668-wifi-drv.git;protocol=ssh;branch=main"
SRCREV = "d3af91b0fdf128a0f48b4c3a8b180ba2b159ba05"

S = "${WORKDIR}/git"

EXTRA_OEMAKE+=" \
	MTK_COMBO_CHIP=MT6632 \
	CONFIG_MTK_WIFI_ONLY=m \
	CONFIG_MTK_COMBO=m \
	CONFIG_MTK_COMBO_WIFI=m \
	CONFIG_MTK_COMBO_WIFI_HIF=sdio \
	WLAN_CHIP_ID=mt7668 \
"

RDEPENDS_${PN} += "mt7668-wifi-fw"
RPROVIDES_${PN} += "kernel-module-mt7668-wifi"
