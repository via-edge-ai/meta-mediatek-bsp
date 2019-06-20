# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.53"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=pumpkin-v4.19"
SRCREV = "686f64648cd0793d3f843f4617d53715544ed51f"

SRC_URI_append = " \
	file://defconfig \
"
