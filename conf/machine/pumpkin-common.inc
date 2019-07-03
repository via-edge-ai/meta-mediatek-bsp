require conf/machine/include/tune-cortexa35.inc

# Kernel
KERNEL_IMAGETYPE = "fitImage"
KERNEL_CLASSES += "kernel-fitimage"
DTB_LOADADDRESS = "0x44000000"
KERNEL_COMPRESSION = "gzip"

# Bootloader
UBOOT_ENTRYPOINT = "0x40080000"
UBOOT_LOADADDRESS = "0x40080000"
UBOOT_MACHINE = "pumpkin_emmc_defconfig"
UBOOT_SUFFIX = "bin"

IMAGE_BOOT_FILES = " \
	${KERNEL_IMAGETYPE} \
"

PREFERRED_PROVIDER_virtual/kernel ??= "linux-mtk"
PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot"
PREFERRED_PROVIDER_virtual/tf-a ??= "tf-a"

PREFERRED_VERSION_linux-mtk ?= "4.19%"

MACHINE_FEATURES_append = " alsa bluetooth usbgadget wifi"

IMAGE_FSTYPES ?= "ext4"
IMAGE_ROOTFS_ALIGNMENT = "4"

SERIAL_CONSOLES = "921600;ttyS0"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	packagegroup-mtk-wireless \
"

DISTRO_FEATURES_append = " optee"

EXTRA_IMAGEDEPENDS = " \
	lk \
	virtual/tf-a \
	mbr \
	mtk-flash-tools \
"
