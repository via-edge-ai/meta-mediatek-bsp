# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
inherit kernel-fitimage-with-dtbo

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION_EXTENSION = "-mtk"

S = "${WORKDIR}/git"

SRC_URI:append = " \
	${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'file://optee.cfg', '', d)} \
"

# Board specific config fragments
SRC_URI:append:mt8365-evk = " file://mt8365-evk.cfg "
SRC_URI:append:mt8365-sb35 = " file://mt8365-sb35.cfg "
SRC_URI:append:mt8365-pumpkin = " file://mt8365-pumpkin.cfg "
SRC_URI:append:mt8183-pumpkin = " file://mt8183-pumpkin.cfg "
SRC_URI:append:mt8183-evb = " file://mt8183-evb.cfg "
SRC_URI:append:mt8167-pumpkin = " file://mt8167-pumpkin.cfg "
SRC_URI:append:mt8167-sb30 = " file://mt8167-sb30.cfg "
SRC_URI:append:mt8167-coral = " file://mt8167-coral.cfg "
SRC_URI:append:i300-pumpkin = " \
	file://i300-pumpkin.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'file://vesper.cfg', '', d)} \
"

# SoC specific config fragments
SRC_URI:append:mt8167 = " file://mt8167.cfg file://mt8516.cfg "
SRC_URI:append:mt8183 = " file://mt8183.cfg "
SRC_URI:append:mt8365 = " file://mt8365.cfg "
SRC_URI:append:mt8516 = " file://mt8516.cfg "

# Connectivity config fragments
SRC_URI:append:mt7663 = " \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "file://mt7668.cfg", "file://mt7663.cfg", d)} \
"
SRC_URI:append:mt7668 = " file://mt7668.cfg "

KERNEL_EXTRA_ARGS = "Image.gz dtbs"
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

export DTC_FLAGS = '${@bb.utils.contains("DISTRO_FEATURES", "dtbo", "-@", "", d)}'

SRC_URI = "${AIOT_BSP_URI}/linux.git;protocol=ssh;branch=${SRCBRANCH}"
PV = "${LINUX_VERSION}+git${SRCPV}"

uboot_prep_kimage() {
	linux_comp="gzip"
	cp arch/arm64/boot/Image.gz linux.bin
	echo ${linux_com}
}

COMPATIBLE_MACHINE_mediatek-bsp = "mediatek-bsp"
