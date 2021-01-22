# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-os.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "a2f1cc11b20bf18c120801785e3ef0f73d044dd9"
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
