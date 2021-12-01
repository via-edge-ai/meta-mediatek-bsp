# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-os.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "290c86d8becf5d81969b034b86ebc6c7859bbce1"
SRC_URI = "${AIOT_BSP_URI}/optee-os.git;protocol=ssh;branch=mtk-v3.15"

SRC_URI += " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
	file://0002-allow-setting-sysroot-for-clang.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

# set dram size
EXTRA_OEMAKE += "CFG_DRAM_SIZE=${OPTEE_DRAM_SIZE}"
