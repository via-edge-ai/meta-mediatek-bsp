# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek DDR configuration library"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit meson

EXTRA_OEMESON_append = " -Dboard=${LIBDRAM_BOARD_NAME}"

S = "${WORKDIR}/git"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/mtk-libdram.git;protocol=ssh"
SRCREV = "e82193cbaf02aa03bba206a3c8bcd94d7c998f3b"

SECURITY_CFLAGS = ""

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"
