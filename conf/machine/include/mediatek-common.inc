MACHINEOVERRIDES =. "mtk:"

# Kernel
KERNEL_CLASSES = "kernel-fitimage-mtk"
KERNEL_IMAGETYPE = "fitImage"
KERNEL_COMPRESSION = "gzip"

# U-Boot
UBOOT_SUFFIX = "bin"
UBOOT_ENV_SUFFIX = "script"
UBOOT_ENV = "boot"
UBOOT_ENTRYPOINT       = "0x40200000"
UBOOT_LOADADDRESS      = "0x40200000"
UBOOT_DTB_LOADADDRESS  = "0x44000000"
UBOOT_DTBO_LOADADDRESS = "0x44c00000"

# OP-TEE OS
PREFERRED_PROVIDER:virtual/optee-os:mtk ?= "optee-os"
PREFERRED_VERSION:optee-client:mtk ?= "3.16"
PREFERRED_VERSION:optee-examples:mtk ?= "3.16"
PREFERRED_VERSION:optee-os:mtk ?= "3.16"
PREFERRED_VERSION:optee-os-tadevkit:mtk ?= "3.16"
PREFERRED_VERSION:optee-test:mtk ?= "3.16"
OPTEE_TZDRAM_START ?= "0x43200000"
OPTEE_TZDRAM_SIZE  ?= "0x00a00000"

IMAGE_BOOT_FILES = " \
	${KERNEL_IMAGETYPE} \
"

IMAGE_FSTYPES ?= "wic.img"
IMAGE_ROOTFS_ALIGNMENT = "4"
IMAGE_CLASSES += "image_type_img image_type_aiotflash"

SERIAL_CONSOLES = "921600;ttyS0"

WKS_FILE ?= "mediatek.wks"
WIC_CREATE_EXTRA_ARGS += "-i direct-with-blksz"
WIC_SECTOR_SIZE ?= "512"
WIC_BLOCK_SIZE ?= "1024"
WICVARS:append = " WIC_SECTOR_SIZE WIC_BLOCK_SIZE"
do_image_wic[depends] += " \
	virtual/trusted-firmware-a:do_deploy \
	virtual/kernel:do_deploy \
"

PREFERRED_PROVIDER_virtual/dtb = "dtbo"
PREFERRED_PROVIDER_virtual/kernel ??= "linux-mtk"
PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot"
PREFERRED_PROVIDER_virtual/trusted-firmware-a ??= "trusted-firmware-a"
PREFERRED_PROVIDER_virtual/lk ??= "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "lk", "lk-prebuilt", d)}"

PREFERRED_VERSION_linux-mtk ??= "5.10%"

EXTRA_IMAGEDEPENDS = " \
	virtual/lk \
	virtual/trusted-firmware-a \
	rity-tools \
"

MACHINE_EXTRA_RRECOMMENDS += " \
	kernel-modules \
	packagegroup-mtk \
"

# Set this only for factory image
# An image built with this option should not be shipped to customers
OPTEE_RPMB_WRITE_KEY ??= "0"
# Set this only to use the RPMB test key instead of generating an unique one
OPTEE_RPMB_TEST_KEY ??= "0"
