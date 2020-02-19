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

my_postprocess_rootfs() {

	if ${@bb.utils.contains('DISTRO_FEATURES','wayland','true','false',d)}; then
		echo "mkdir -p /run/user/`id -u`" >> ${IMAGE_ROOTFS}/etc/profile
		echo "export XDG_RUNTIME_DIR=/run/user/`id -u` " >> ${IMAGE_ROOTFS}/etc/profile
		mkdir -p ${IMAGE_ROOTFS}/etc/xdg/weston/
		echo "[core]" > ${IMAGE_ROOTFS}/etc/xdg/weston/weston.ini
		echo "idle-time=0" >> ${IMAGE_ROOTFS}/etc/xdg/weston/weston.ini
		echo "require-input=false" >> ${IMAGE_ROOTFS}/etc/xdg/weston/weston.ini
	fi

}

ROOTFS_POSTPROCESS_COMMAND += "my_postprocess_rootfs; "

