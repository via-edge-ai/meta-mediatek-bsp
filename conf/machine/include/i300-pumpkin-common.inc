require mt7668.inc

MACHINEOVERRIDES =. "i300-pumpkin:"

# Kernel
DTB_LOADADDRESS = "0x44000000"
KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
	${@bb.utils.contains("MACHINE_FEATURES", "vesper-hat", "vesper.dtbo", "", d)} \
	${@bb.utils.contains("I300_PUMPKIN_AUDIO_CONF", "i2s", "audio-i2s.dtbo", "", d)} \
"

# U-Boot
UBOOT_ENTRYPOINT = "0x40200000"
UBOOT_LOADADDRESS = "0x40200000"
UBOOT_DTB_LOADADDRESS = "0x44000000"
UBOOT_DTBO_LOADADDRESS = "0x44c00000"
UBOOT_MACHINE = "mt8516_pumpkin_defconfig"

# TF-A priv
TFA_BOARD_NAME = "mt8516-pumpkin"

# libdram
LIBDRAM_BOARD_NAME = "mt8516-pumpkin"

# LK
LK_PROJECT = "pumpkin8516-emmc"

MACHINE_FEATURES:append = " alsa bluetooth usbgadget usbhost wifi"

DISTRO_FEATURES:append = " dtbo "
