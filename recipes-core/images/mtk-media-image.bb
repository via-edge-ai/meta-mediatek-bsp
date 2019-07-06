# Copyright (C) 2018 Phong LE <ple@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Multimedia image with some development tools"

require mtk-image.inc

IMAGE_INSTALL += "\
	packagegroup-pumpkin-zeroconf \
	packagegroup-pumpkin-audio \
	packagegroup-media \
"
