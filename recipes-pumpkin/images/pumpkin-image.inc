# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL = "\
	packagegroup-base \
	packagegroup-core-boot \
	packagegroup-core-full-cmdline \
	busybox \
	dropbear \
"

PACKAGE_EXCLUDE = "openssh"

inherit core-image
