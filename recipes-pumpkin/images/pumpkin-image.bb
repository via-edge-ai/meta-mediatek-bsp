# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Pumpkin image with some development tools"

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL = "\
	packagegroup-core-boot \
	packagegroup-core-full-cmdline \
	packagegroup-pumpkin-wireless \
	packagegroup-pumpkin-zeroconf \
	packagegroup-pumpkin-audio \
	busybox \
	dropbear \
"

PACKAGE_EXCLUDE = "openssh"

inherit core-image
