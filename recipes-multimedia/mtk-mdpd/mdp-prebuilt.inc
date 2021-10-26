# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek pre-built binaries for MDP"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_RITY_URI}/mdp-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "ed711e39861632dcfa2c21ad561d05b8bd59483d"

S = "${WORKDIR}/git"

INSANE_SKIP:${PN} += " already-stripped "
INSANE_SKIP:${PN}-dev += " dev-elf "
