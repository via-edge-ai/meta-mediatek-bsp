# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-mtk-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.10.104"
SRCBRANCH ?= "mtk-v5.10"
SRCREV = "93fe3cb3ddeb8c60fa7fa310bb586ee59ff83f7d"

SRC_URI:append = " \
	file://0001-WORKAROUND-drm-mediatek-mt8365-disable-OVL-for-DSI-p.patch \
"
