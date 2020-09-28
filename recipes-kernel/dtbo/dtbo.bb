# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

SRC_URI_append_i300-pumpkin = " \
	file://vesper.dts \
"

SRC_URI_append_mt8167-pumpkin = " \
	file://rpi-display.dts \
"

SRC_URI_append_mt8167-sb30 = " \
	file://mt8167-sb30_rpi-display.dts \
"

SRC_URI_append_mt8183-evb = " \
	file://panel-tpv-otm1901a.dts \
	file://panel-truly-r63350a.dts \
"

SRC_URI_append_mt8183-pumpkin = " \
	file://panel-urt-umo9465md.dts \
	file://mt8183-pumpkin-ar0330.dts \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
