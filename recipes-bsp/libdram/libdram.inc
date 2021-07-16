# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek DDR configuration library"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit meson

EXTRA_OEMESON_append = " -Dboard=${LIBDRAM_BOARD_NAME}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_URI}/device/mtk-libdram.git;protocol=ssh"
SRCREV = "12f5e48d3959b4ba16a794079cab02dc45e873c8"

SECURITY_CFLAGS = ""

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"
