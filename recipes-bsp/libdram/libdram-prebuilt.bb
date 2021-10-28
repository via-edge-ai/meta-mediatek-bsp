# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek Prebuilt DDR configuration library"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_RITY_URI}/libdram-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "353a5de95b8cdcfb47cc5b6bbeaf6ab0fb183d5d"

do_install() {
	install -d ${D}${libdir}
	install -m 644 ${S}/${LIBDRAM_BOARD_NAME}/libdram.a ${D}${libdir}
}
