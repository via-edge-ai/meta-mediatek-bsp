# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek pre-built binaries for MDP"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/mdp-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "272d130f8e1182632e093b0e081229f26102a01d"

S = "${WORKDIR}/git"

INSANE_SKIP:${PN} += " already-stripped "
INSANE_SKIP:${PN}-dev += " dev-elf "
