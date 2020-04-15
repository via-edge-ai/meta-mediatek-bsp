require mt7668.inc

MACHINEOVERRIDES =. "i300-pumpkin:"

# Kernel
DTB_LOADADDRESS = "0x44000000"
KERNEL_DEVICETREE_OVERLAYS += "vesper.dtbo"
KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
	${@bb.utils.contains("MACHINE_FEATURES", "vesper-hat", "vesper.dtbo", "", d)} \
"

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

MACHINE_FEATURES_append = " alsa bluetooth usbgadget usbhost wifi"

DISTRO_FEATURES_append = " dtbo "
