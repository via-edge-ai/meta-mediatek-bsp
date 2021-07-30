# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek pre-built binaries for MDP"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_RITY_URI}/mdp-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "827bd4c1a2d9692d67e1c7952a6301faf8afba82"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN} += " already-stripped "
INSANE_SKIP_${PN}-dev += " dev-elf "
