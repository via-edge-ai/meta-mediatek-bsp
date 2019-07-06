# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "MediaTek image with some development tools"

require mtk-image.inc

IMAGE_INSTALL += "\
	packagegroup-pumpkin-zeroconf \
	packagegroup-pumpkin-audio \
"
