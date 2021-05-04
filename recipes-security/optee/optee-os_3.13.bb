# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-os.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "1b79cb993a49acf754da22237e70f9e19708db1f"
SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.13"

SRC_URI_append = " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
	file://0002-allow-setting-sysroot-for-clang.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

# set dram size
EXTRA_OEMAKE += "CFG_DRAM_SIZE=${OPTEE_DRAM_SIZE}"
