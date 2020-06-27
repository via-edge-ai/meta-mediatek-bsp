# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.148"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v4.19"
SRCREV = "6386d91a78fbb13e3f1dbb46e18230950003cc78"
