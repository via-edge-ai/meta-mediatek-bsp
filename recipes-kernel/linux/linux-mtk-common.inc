# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
inherit kernel-fitimage-with-dtbo

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION_EXTENSION = "-mtk"

S = "${WORKDIR}/git"

SRC_URI_append = " \
	file://defconfig \
	file://usb_uvc.cfg \
	${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'file://optee.cfg', '', d)} \
"

SRC_URI_append_i500-evb = " file://it66121.cfg "

SRC_URI_append_i300-pumpkin = " \
	${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'file://vesper.cfg', '', d)} \
"

KERNEL_EXTRA_ARGS = "Image.gz dtbs"
KCONFIG_MODE = "--alldefconfig"

export DTC_FLAGS = '${@bb.utils.contains("DISTRO_FEATURES", "dtbo", "-@", "", d)}'

PV = "${LINUX_VERSION}+git${SRCPV}"

uboot_prep_kimage() {
	linux_comp="gzip"
	cp arch/arm64/boot/Image.gz linux.bin
	echo ${linux_com}
}

COMPATIBLE_MACHINE_mediatek-bsp = "mediatek-bsp"
