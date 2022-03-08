# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-os.inc

DEPENDS:append = " python3-cryptography-native "

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "f8404fd7e8f21e523cdd6d7f1770c40a6dd04c6c"
SRC_URI = "${AIOT_BSP_URI}/optee-os.git;protocol=ssh;branch=mtk-v3.16"

SRC_URI += " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
	file://0002-allow-setting-sysroot-for-clang.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

# set dram size
EXTRA_OEMAKE += " \
        CFG_DRAM_SIZE=${OPTEE_DRAM_SIZE} \
        CFG_TZDRAM_START=${OPTEE_TZDRAM_START} \
        CFG_TZDRAM_SIZE=${OPTEE_TZDRAM_SIZE} \
"
