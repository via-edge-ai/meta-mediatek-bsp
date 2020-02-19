# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL = "\
	packagegroup-base \
	packagegroup-core-boot \
	packagegroup-core-full-cmdline \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "optee-client", "", d)} \
"

inherit core-image
