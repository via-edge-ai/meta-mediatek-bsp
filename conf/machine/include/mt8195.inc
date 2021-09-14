require conf/machine/include/arm/arch-armv8a.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8195"

# Trusted Firmware-A
TFA_PLATFORM = "mt8195"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8195"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8395-demo"

DISTRO_FEATURES:append = " optee "
DISTRO_FEATURES:remove = " x11 "

MACHINEOVERRIDES =. "mt8395:i1200:"
