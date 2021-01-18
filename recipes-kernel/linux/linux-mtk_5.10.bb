# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.10"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/linux.git;protocol=ssh;branch=mtk-v5.10"
SRCREV = "060004d204792205e05672d376d16c0587bceee3"
