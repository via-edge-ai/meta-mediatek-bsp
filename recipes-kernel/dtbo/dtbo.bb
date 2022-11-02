# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"
inherit devicetree

FILESEXTRAPATHS:prepend := "${THISDIR}/common:"
FILESEXTRAPATHS:prepend:mt8167-coral := "${THISDIR}/mt8167-coral:"
FILESEXTRAPATHS:prepend:mt8167-pumpkin := "${THISDIR}/mt8167-pumpkin:"
FILESEXTRAPATHS:prepend:mt8167-sb30 := "${THISDIR}/mt8167-sb30:"
FILESEXTRAPATHS:prepend:mt8516-pumpkin:= "${THISDIR}/mt8516-pumpkin:"
FILESEXTRAPATHS:prepend:mt8183-evb := "${THISDIR}/mt8183-evb:"
FILESEXTRAPATHS:prepend:mt8183-pumpkin:= "${THISDIR}/mt8183-pumpkin:"
FILESEXTRAPATHS:prepend:mt8365-evk := "${THISDIR}/mt8365-evk:"
FILESEXTRAPATHS:prepend:mt8365-pumpkin := "${THISDIR}/mt8365-pumpkin:"
FILESEXTRAPATHS:prepend:mt8365-sb35:= "${THISDIR}/mt8365-sb35:"
FILESEXTRAPATHS:prepend:mt8195-demo:= "${THISDIR}/mt8195-demo:"
FILESEXTRAPATHS:prepend:genio-700-evk:= "${THISDIR}/genio-700-evk:"

do_compile[depends] += "virtual/kernel:do_shared_workdir"
KERNEL_INCLUDE:append = " \
	${STAGING_KERNEL_BUILDDIR}/include \
	${STAGING_KERNEL_BUILDDIR}/include/generated \
"

SRC_URI:append = " \
	file://panel-raspberrypi.dtsi \
"

SRC_URI:append:mt8167-pumpkin = " \
	file://panel-raspberrypi.dts \
	file://panel-avd-tt70ws-cn-134-a.dts \
	file://vesper.dts \
	file://audio-i2s.dts \
	file://camera-ov5645.dts \
"

SRC_URI:append:mt8167-sb30 = " \
	file://panel-raspberrypi.dts \
"

SRC_URI:append:mt8167-coral = " \
	file://camera-ov5645.dts \
"

SRC_URI:append:mt8183-evb = " \
	file://panel-tpv-otm1901a.dts \
	file://panel-truly-r63350a.dts \
"

SRC_URI:append:mt8183-pumpkin = " \
	file://panel-raspberrypi.dts \
	file://panel-urt-umo9465md.dts \
	file://camera-ar0330-dual.dtsi \
	file://camera-ar0330-single.dts \
	file://camera-ar0330-dual.dts \
	file://camera-ap1302-ar0330-dual-ar0144-dual.dtsi \
	file://camera-ap1302-ar0330-single.dts \
	file://camera-ap1302-ar0144-single.dts \
	file://camera-ap1302-ar0144-dual.dts \
	file://camera-ap1302-ar0330-single-ar0144-single.dts \
	file://camera-ap1302-ar0330-single-ar0144-dual.dts \
	file://camera-ap1302-ar1335-single.dts \
        file://camera-ap1302-ar0430-dual.dtsi \
        file://camera-ap1302-ar0430-single.dts \
        file://camera-ap1302-ar0430-dual.dts \
"

SRC_URI:append:mt8365-evk = " \
	file://net-ethernet.dts \
	file://camera-ap1302-ar0430-dual.dtsi \
	file://camera-ap1302-ar0430-single-csi0.dts \
	file://camera-ap1302-ar0430-single-csi1.dts \
	file://panel-startek-kd070fhfid015.dts \
	file://video.dts \
	file://gpu-mali.dts \
"

SRC_URI:append:mt8365-pumpkin = " \
        file://camera-ap1302-ar0144-dual.dtsi \
        file://camera-ap1302-ar0144-dual.dts \
        file://camera-ap1302-ar0144-single.dts \
        file://camera-ap1302-ar0430-dual.dtsi \
        file://camera-ap1302-ar0430-dual.dts \
        file://camera-ap1302-ar0430-single.dts \
        file://camera-ap1302-ar0330-dual.dtsi \
        file://camera-ap1302-ar0330-dual.dts \
        file://camera-ap1302-ar0330-single.dts \
        file://camera-ap1302-ar0330-dual-ar0144-dual.dtsi \
        file://camera-ap1302-ar0330-dual-ar0144-dual.dts \
        file://camera-ap1302-ar0330-single-ar0144-dual.dts \
        file://camera-ap1302-ar0330-single-ar0144-single.dts \
        file://camera-ap1302-ar0330-dual-ar0430-dual.dtsi \
        file://camera-ap1302-ar0330-dual-ar0430-dual.dts \
        file://camera-ap1302-ar0330-single-ar0430-dual.dts \
        file://camera-ap1302-ar0330-single-ar0430-single.dts \
	file://gpu-mali.dts \
"

SRC_URI:append:mt8365-sb35 = " \
	file://panel-raspberrypi.dts \
	file://gpu-mali.dts \
"

SRC_URI:append:mt8516-pumpkin = " \
	file://vesper.dts \
	file://audio-i2s.dts \
"

SRC_URI:append:mt8195-demo = " \
	file://panel-boe-ne135fbm.dts \
	file://video.dts \
	file://display-dp.dts \
	file://display-dsi.dts \
	file://display-edp4k.dts \
	file://gpu-mali.dts \
	file://isp70.dtsi \
	file://camera-imx214-csi0.dts \
	file://mtk-camera.dtsi \
"
SRC_URI:append:genio-700-evk = " \
	file://gpu-mali.dts \
	file://apusys.dts \
"

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
