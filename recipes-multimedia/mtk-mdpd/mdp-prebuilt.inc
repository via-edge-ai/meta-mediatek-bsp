# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek pre-built binaries for MDP"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_RITY_URI}/mdp-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "2dddc715279b809462d65fa3e7c3789de2dc1d25"

S = "${WORKDIR}/git"

INSANE_SKIP:${PN} += " already-stripped "
INSANE_SKIP:${PN}-dev += " dev-elf "
