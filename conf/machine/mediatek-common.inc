
# Kernel
KERNEL_IMAGETYPE = "fitImage"
KERNEL_COMPRESSION = "gzip"

# U-Boot
UBOOT_SUFFIX = "bin"

IMAGE_BOOT_FILES = " \
	${KERNEL_IMAGETYPE} \
"

IMAGE_FSTYPES ?= "ext4 wic"
IMAGE_ROOTFS_ALIGNMENT = "4"

SERIAL_CONSOLES = "921600;ttyS0"

WKS_FILE ?= "mediatek.wks"
do_image_wic[depends] += "virtual/tf-a:do_deploy linux-mtk:do_deploy"

PREFERRED_PROVIDER_virtual/kernel ??= "linux-mtk"
PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot"
PREFERRED_PROVIDER_virtual/tf-a ??= "tf-a"

EXTRA_IMAGEDEPENDS = " \
	lk \
	virtual/tf-a \
	mtk-flash-tools \
"

MACHINE_EXTRA_RRECOMMENDS += " \
	kernel-modules \
	packagegroup-mtk \
"
