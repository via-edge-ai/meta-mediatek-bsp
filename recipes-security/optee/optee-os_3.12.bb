# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-os.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "8814ef1511beca073e19d736ed06f4dbf4a41df3"
SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.12"

SRC_URI_append = " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
	file://0002-allow-setting-sysroot-for-clang.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

# set dram size
EXTRA_OEMAKE += "CFG_DRAM_SIZE=${OPTEE_DRAM_SIZE}"
