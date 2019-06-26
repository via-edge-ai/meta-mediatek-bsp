# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.56"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=pumpkin-v4.19"
SRCREV = "55f62f3637fc8003bc35188fb2b49a19bc11230f"

SRC_URI_append = " \
	file://defconfig \
"
