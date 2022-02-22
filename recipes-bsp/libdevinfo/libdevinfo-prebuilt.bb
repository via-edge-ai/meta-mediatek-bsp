# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek Prebuilt device information library"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_BSP_URI}/libdevinfo-prebuilt.git;protocol=ssh;branch=master"
SRCREV = "855414c3e6a788a042b4ce1ab789506c4709b7fe"

do_install() {
	install -d ${D}${libdir}
	install -m 644 ${S}/${SOC_FAMILY}/libdevinfo.a ${D}${libdir}
}
