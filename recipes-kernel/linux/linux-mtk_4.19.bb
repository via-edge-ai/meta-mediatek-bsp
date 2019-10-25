# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.80"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v4.19"
SRCREV = "947b9d84af7d2dfc2ed53d2ccc2f087973e6025e"

SRC_URI_append = " \
	file://defconfig \
	file://usb_uvc.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'file://ts_ft5x06.cfg', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'file://optee.cfg', '', d)} \
"
