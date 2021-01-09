# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

FILESEXTRAPATHS_prepend := "${THISDIR}/common:"
FILESEXTRAPATHS_prepend_mt8167-coral := "${THISDIR}/mt8167-coral:"
FILESEXTRAPATHS_prepend_mt8167-pumpkin := "${THISDIR}/mt8167-pumpkin:"
FILESEXTRAPATHS_prepend_mt8167-sb30 := "${THISDIR}/mt8167-sb30:"
FILESEXTRAPATHS_prepend_mt8516-pumpkin:= "${THISDIR}/mt8516-pumpkin:"
FILESEXTRAPATHS_prepend_mt8183-evb := "${THISDIR}/mt8183-evb:"
FILESEXTRAPATHS_prepend_mt8183-pumpkin:= "${THISDIR}/mt8183-pumpkin:"

do_compile[depends] += "virtual/kernel:do_shared_workdir"
KERNEL_INCLUDE_append = " \
	${STAGING_KERNEL_BUILDDIR}/include \
	${STAGING_KERNEL_BUILDDIR}/include/generated \
"

SRC_URI_append = " \
	file://panel-raspberrypi.dtsi \
"

SRC_URI_append_mt8167-pumpkin = " \
	file://panel-raspberrypi.dts \
	file://panel-avd-tt70ws-cn-134-a.dts \
	file://vesper.dts \
	file://camera-ov5645.dts \
"

SRC_URI_append_mt8167-sb30 = " \
	file://panel-raspberrypi.dts \
"

SRC_URI_append_mt8167-coral = " \
	file://camera-ov5645.dts \
"

SRC_URI_append_mt8183-evb = " \
	file://panel-tpv-otm1901a.dts \
	file://panel-truly-r63350a.dts \
"

SRC_URI_append_mt8183-pumpkin = " \
	file://panel-raspberrypi.dts \
	file://panel-urt-umo9465md.dts \
	file://camera-ar0330-single.dts \
	file://camera-ar0330-dual.dts \
	file://camera-ap1302-ar0330-single-ar0144-single.dts \
"

SRC_URI_append_mt8516-pumpkin = " \
	file://vesper.dts \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
