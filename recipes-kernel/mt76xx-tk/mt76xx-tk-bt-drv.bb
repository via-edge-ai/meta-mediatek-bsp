# Copyright (C) 2023 MediaTek, Inc.
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MT7663 Bluetoth driver"
LICENSE = "CLOSED"

inherit module

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_driver/turnkey_sdio;protocol=ssh;branch=main"
SRCREV = "79b02e3d5a4c208a932984a35d9eb74c57c63e3f"

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