# Copyright (C) 2021 Ryan Cho <ryan.cho@mediatek.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek Prebuilt Basic configuration library"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_RITY_URI}/libbase-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "91f5d1dba3595d34c7a03764c85b9ae1a6678319"
