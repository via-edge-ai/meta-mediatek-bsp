# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "MediaTek image with some development tools"

require mtk-image.inc

WAYLAND_PACKAGES = " \
	wayland \
	weston \
	weston-init \
"

IMAGE_INSTALL += "\
	packagegroup-zeroconf \
	packagegroup-audio \
	${@bb.utils.contains("DISTRO_FEATURES", "wayland", "${WAYLAND_PACKAGES}", "", d)} \
"
