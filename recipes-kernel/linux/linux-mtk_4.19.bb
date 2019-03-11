# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.28"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=pumpkin-v4.19"
SRCREV = "2e22464b5df624db27d4d973e529e08d25feb71a"

SRC_URI_append = " \
	file://defconfig \
"
