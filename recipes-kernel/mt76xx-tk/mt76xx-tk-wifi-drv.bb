# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MT7668 Wi-Fi driver"
LICENSE = "CLOSED"

inherit module

SRC_URI = "${AIOT_NDA_URI}/mt7668-wifi-drv.git;protocol=ssh;branch=${MTK_WIRELESS_RELEASE}"
SRCREV = "6db10c734f8b05336fee49b512df7b4b27035350"

S = "${WORKDIR}/git"

EXTRA_OEMAKE+=" \
	MTK_PLATFORM= \
	WLAN_CHIP_ID=${MTK_WIRELESS_CHIP}\
"

RDEPENDS:${PN}:mt7668 += "mt7668-tk-wifi-fw"
RDEPENDS:${PN}:mt7663 += "mt7663-tk-wifi-fw"
RPROVIDES:${PN} += " \
	kernel-module-mt7663-tk-wifi \
	kernel-module-mt7668-tk-wifi \
"
