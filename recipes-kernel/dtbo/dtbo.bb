# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

VESPER_MIC_CONFIG ??= "6_1"

DTC_PPFLAGS_append_pumpkin = " \
	-DVESPER_${VESPER_MIC_CONFIG} \
"

SRC_URI_append_pumpkin = " \
	file://vesper.dts \
"

SRC_URI_append_mt8167-pumpkin = " \
	file://rpi-display.dts \
"

SRC_URI_append_mt8183-evb = " \
	file://panel-tpv-otm1901a.dts \
	file://panel-truly-r63350a.dts \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
