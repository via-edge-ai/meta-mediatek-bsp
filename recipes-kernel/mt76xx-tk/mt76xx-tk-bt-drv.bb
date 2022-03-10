# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MT7663 Bluetoth driver"
LICENSE = "CLOSED"

inherit module

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_driver/turnkey_sdio;protocol=ssh;branch=main"
SRCREV = "b5a594c56b4582737e58546f33eefd134741a295"

S = "${WORKDIR}/git"

EXTRA_OEMAKE+=" \
	MTK_PLATFORM= \
	WLAN_CHIP_ID=${MTK_WIRELESS_CHIP}\
"

do_install:append() {
	install -d ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/cfg/bt.cfg ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} = " \
	${nonarch_base_libdir}/firmware/bt.cfg \
"

RPROVIDES:${PN} += " \
	kernel-module-mt7663-tk-bt \
	kernel-module-mt7668-tk-bt \
"
