# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LINUX_VERSION ?= "5.4.51"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v5.4"
SRCREV = "50ca5f1c95d808ca06f2be03467f5a4c02638853"
