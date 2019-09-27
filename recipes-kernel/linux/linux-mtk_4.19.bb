# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.75"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v4.19"
SRCREV = "a0ab04676c9c6ef9d8433ffda7a10e357601c623"

SRC_URI_append = " \
	file://defconfig \
	${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'file://optee.cfg', '', d)} \
"
