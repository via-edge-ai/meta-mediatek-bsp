# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.34"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=pumpkin-v4.19"
SRCREV = "ea21365fd6007f057a51bce60cd02"

SRC_URI_append = " \
	file://defconfig \
"
