require conf/machine/include/arm/arch-arm64.inc

# Kernel
KERNEL_IMAGETYPE = "fitImage"
KERNEL_CLASSES += "kernel-fitimage"
DTB_LOADADDRESS = "0x44000000"

# Bootloader
UBOOT_ENTRYPOINT = "0x40080000"
UBOOT_LOADADDRESS = "0x40080000"

IMAGE_BOOT_FILES = " \
	${KERNEL_IMAGETYPE} \
"

PREFERRED_PROVIDER_virtual/kernel ??= "linux-mtk"
PREFERRED_PROVIDER_virtual/bootloader ??= "lk"

MACHINE_FEATURES_append = " alsa bluetooth usbgadget wifi"

IMAGE_FSTYPES ?= "ext4"
IMAGE_ROOTFS_ALIGNMENT = "4"

SERIAL_CONSOLES = "921600;ttyS0"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	packagegroup-mtk-wireless \
"

EXTRA_IMAGEDEPENDS = " \
	lk \
	mbr \
	mtk-flash-tools \
	trustzone \
"
