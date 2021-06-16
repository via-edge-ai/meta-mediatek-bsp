# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek DDR configuration library"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit meson

EXTRA_OEMESON_append = " -Dboard=${LIBDRAM_BOARD_NAME}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/libdram.git;protocol=ssh;branch=main"
SRCREV = "b1c3f5111f7fd4adaff052075dadcb3bc36b15f5"

SECURITY_CFLAGS = ""

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"
