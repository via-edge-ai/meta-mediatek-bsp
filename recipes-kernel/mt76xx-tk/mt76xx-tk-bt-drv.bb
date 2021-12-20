# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MT7663 Bluetoth driver"
LICENSE = "CLOSED"

inherit module allarch

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_driver/turnkey_sdio;protocol=ssh;branch=main"
#SRCREV = "2bd038c540ce535d102d88bafa71506f56fcc9e7"
SRCREV = "${AUTOREV}"


S = "${WORKDIR}/git"

EXTRA_OEMAKE+=" \
	MTK_PLATFORM= \
	WLAN_CHIP_ID=${MTK_WIRELESS_CHIP}\
"
do_compile[nostamp] = "1"

do_install_append() {
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
