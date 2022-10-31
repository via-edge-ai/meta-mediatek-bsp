# Copyright (C) 2022 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.15.37"
SRCBRANCH ?= "mtk-v5.15-dev"
SRCREV = "953cae512d0ca8d3f176867f41168895ef9b4cf4"

DEPENDS += "rsync-native"

do_install:append() {
	oe_runmake headers_install INSTALL_HDR_PATH=${STAGING_KERNEL_BUILDDIR}${exec_prefix}
}
