require conf/machine/include/tune-cortexa35.inc
require mediatek-common.inc

# Kernel
DTB_LOADADDRESS = "0x44000000"

# U-Boot
UBOOT_ENTRYPOINT = "0x40080000"
UBOOT_LOADADDRESS = "0x40080000"
UBOOT_MACHINE = "pumpkin_emmc_defconfig"

# Trusted Firmware-A
TFA_PLAT = "mt8516"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8516"

# LK
LK_PROJECT = "pumpkin8516-emmc"

PREFERRED_VERSION_linux-mtk ?= "4.19%"

MACHINE_FEATURES_append = " alsa bluetooth usbgadget wifi"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	packagegroup-mtk-wireless \
"

DISTRO_FEATURES_append = " optee"
