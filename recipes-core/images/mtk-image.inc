# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL = "\
	packagegroup-base \
	packagegroup-core-boot \
	packagegroup-core-full-cmdline \
	busybox \
	dropbear \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "optee-client", "", d)} \
"

PACKAGE_EXCLUDE = "openssh"

do_gpt() {
	dd if="${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.wic" \
	   of="${IMGDEPLOYDIR}/MBR_EMMC" bs=512 count=34
}

IMAGE_POSTPROCESS_COMMAND += "do_gpt;"

inherit core-image
