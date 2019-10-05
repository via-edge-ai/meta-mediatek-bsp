MACHINEOVERRIDES =. "pumpkin:"

# Kernel
DTB_LOADADDRESS = "0x44000000"
KERNEL_DEVICETREE_OVERLAYS += "vesper.dtbo"

# U-Boot
UBOOT_ENTRYPOINT = "0x40080000"
UBOOT_LOADADDRESS = "0x40080000"
UBOOT_DTB_LOADADDRESS = "0x44000000"
UBOOT_DTBO_LOADADDRESS = "0x44c00000"
UBOOT_MACHINE = "pumpkin_defconfig"

# TF-A priv
TFA_BOARD_NAME = "pumpkin"

# LK
LK_PROJECT = "pumpkin8516-emmc"

PREFERRED_VERSION_linux-mtk ?= "4.19%"

MACHINE_FEATURES_append = " alsa bluetooth usbgadget wifi"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	packagegroup-mtk-wireless \
"

DISTRO_FEATURES_append = " dtbo "
