# Copyright (C) 2021 Ryan Cho <ryan.cho@mediatek.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek Prebuilt Basic configuration library"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6fcd7dfec853b4eca3b44877b51c0943"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_BSP_URI}/libbase-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "eaaf98c9ea097101ce1cc8d684ecc527477c918f"
