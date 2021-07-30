# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MDPD service"
LICENSE = "CLOSED"

RPROVIDES_${PN} += "libmdpd.so()(64bit)"

S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/mdpd.git;protocol=ssh;branch=main"
SRCREV = "cae5cff3af68651f4692adb093db0ff8aa5e7403"

DEBUG_BUILD = "1"

EXTRA_OEMAKE = "mdpd libmdp.mt8167.so"

require mdpd.inc
