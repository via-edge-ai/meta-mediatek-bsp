# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
inherit kernel-fitimage-with-dtbo

DEPENDS += "lz4-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION_EXTENSION = "-mtk"

S = "${WORKDIR}/git"

SRC_URI_append = " \
	file://defconfig \
	file://usb_uvc.cfg \
	${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'file://ts_ft5x06.cfg', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'file://optee.cfg', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'file://vesper.cfg', '', d)} \
"

KERNEL_EXTRA_ARGS = "Image.gz dtbs"
KCONFIG_MODE = "--alldefconfig"

export DTC_FLAGS = '${@bb.utils.contains("DISTRO_FEATURES", "dtbo", "-@", "", d)}'

PV = "${LINUX_VERSION}+git${SRCPV}"

uboot_prep_kimage() {
	linux_comp="${KERNEL_COMPRESSION}"

	if test "${linux_comp}" = "lz4"; then
		linux_suffix=".lz4"
	elif test "${linux_comp}" = "gzip"; then
		linux_suffix=".gz"
	elif test "${linux_comp}" = "none"; then
		linux_suffix=""
	else
		bberror "Invalid kernel compression '${linux_comp}"
	fi

	${OBJCOPY} -O binary -R .note -R .comment -S vmlinux linux.bin.raw

	if test "${linux_comp}" = "lz4"; then
		lz4 -l -c1 linux.bin.raw > linux.bin${linux_suffix}
		# append uncompressed filesize info
		dec_size=0
		fsize=$(stat -c "%s" "linux.bin.raw")
		dec_size=$(expr $dec_size + $fsize)
		printf "%08x\n" $dec_size |
			sed 's/\(..\)/\1 /g' | {
				read ch0 ch1 ch2 ch3;
					for ch in $ch3 $ch2 $ch1 $ch0; do
						printf `printf '%s%03o' '\\' 0x$ch` >> linux.bin${linux_suffix};
					done;
			}
	elif test "${linux_comp}" = "gzip"; then
		cp arch/arm64/boot/Image.gz linux.bin${linux_suffix}
	fi

	if ! test "${linux_comp}" = "none"; then
		mv -f "linux.bin${linux_suffix}" linux.bin
	fi

	echo "${linux_comp}"
}

COMPATIBLE_MACHINE_mediatek-bsp = "mediatek-bsp"
