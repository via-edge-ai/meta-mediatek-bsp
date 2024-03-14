# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MDPD service"
LICENSE = "CLOSED"

RPROVIDES:${PN} += "libmdpd.so()(64bit)"

S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/mdpd.git;protocol=https;branch=main"
SRCREV = "9aff1fb4470ac808b4f548c4c7d27d2f49fdb0a9"

DEBUG_BUILD = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

EXTRA_OEMAKE = " mdpd "

EXTRA_OEMAKE:append:mt8167 = ' \
	libmdp.mt8167.so \
'

EXTRA_OEMAKE:append:mt8365 = ' \
	libmdp.mt8365.so \
	V4l2MDPD_CFLAGS=-DMDP_KERNEL_IPI_COMPATIBLE \
'

require mdpd.inc
