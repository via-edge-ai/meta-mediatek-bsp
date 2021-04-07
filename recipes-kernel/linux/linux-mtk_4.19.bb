# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.184"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v4.19"
SRCREV = "49a1bac0eb9aa9e656723278fd5cabff7829a6b3"
