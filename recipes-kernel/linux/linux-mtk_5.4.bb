# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "5.4.68"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v5.4"
SRCREV = "33dfd8db12fe60947f4ce943ace30730979f725a"
