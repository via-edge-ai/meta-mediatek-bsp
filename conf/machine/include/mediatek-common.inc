
# Kernel
KERNEL_IMAGETYPE = "fitImage"
KERNEL_COMPRESSION = "gzip"

# U-Boot
UBOOT_SUFFIX = "bin"
UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"
UBOOT_ENTRYPOINT       = "0x40200000"
UBOOT_LOADADDRESS      = "0x40200000"
UBOOT_DTB_LOADADDRESS  = "0x44000000"
UBOOT_DTBO_LOADADDRESS = "0x44c00000"

IMAGE_BOOT_FILES = " \
	${KERNEL_IMAGETYPE} \
	${UBOOT_ENV}.${UBOOT_ENV_SUFFIX} \
"

IMAGE_FSTYPES ?= "wic.img"
IMAGE_ROOTFS_ALIGNMENT = "4"
IMAGE_CLASSES += "image_type_img"

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

PREFERRED_PROVIDER_virtual/kernel ??= "linux-mtk"
PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot"
PREFERRED_PROVIDER_virtual/trusted-firmware-a ??= "trusted-firmware-a"
PREFERRED_PROVIDER_virtual/lk ??= "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "lk", "lk-prebuilt", d)}"

EXTRA_IMAGEDEPENDS = " \
	virtual/lk \
	virtual/trusted-firmware-a \
	rity-tools \
"

MACHINE_EXTRA_RRECOMMENDS += " \
	kernel-modules \
	packagegroup-mtk \
	u-boot-scr \
"
