# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Wireless packages for pumpkin board"

inherit packagegroup

WIFI_PKGS = " \
	wpa-supplicant \
"

BT_PKGS = " \
	${@bb.utils.contains("DISTRO_FEATURES", "bluez5", "bluez5", "", d)} \
"

# MT7668
WIFI_PKGS_append_mt7668 = " kernel-module-mt7668-wifi "
BT_PKGS_append_mt7668 = " kernel-module-mt7668-bt "

RDEPENDS_${PN} = " \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "${WIFI_PKGS}", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "${BT_PKGS}", "", d)} \
"
