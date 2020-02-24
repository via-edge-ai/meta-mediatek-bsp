# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "4.19.132"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v4.19"
SRCREV = "a07ded5c73af75cab316214d88cb325a0271458a"
