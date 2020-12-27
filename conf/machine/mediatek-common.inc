
# Kernel
KERNEL_IMAGETYPE = "fitImage"
KERNEL_COMPRESSION = "gzip"

# U-Boot
UBOOT_SUFFIX = "bin"
UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

IMAGE_BOOT_FILES = " \
	${KERNEL_IMAGETYPE} \
	${UBOOT_ENV}.${UBOOT_ENV_SUFFIX} \
"

IMAGE_FSTYPES ?= "ext4 wic"
IMAGE_ROOTFS_ALIGNMENT = "4"

SERIAL_CONSOLES = "921600;ttyS0"

WKS_FILE ?= "mediatek.wks"
do_image_wic[depends] += " \
	virtual/trusted-firmware-a:do_deploy \
	virtual/kernel:do_deploy \
"

PREFERRED_PROVIDER_virtual/kernel ??= "linux-mtk"
PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot"
PREFERRED_PROVIDER_virtual/trusted-firmware-a ??= "trusted-firmware-a"

EXTRA_IMAGEDEPENDS = " \
	lk \
	virtual/trusted-firmware-a \
	mtk-flash-tools \
"

MACHINE_EXTRA_RRECOMMENDS += " \
	kernel-modules \
	packagegroup-mtk \
	u-boot-scr \
"
