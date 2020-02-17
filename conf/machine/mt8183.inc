require conf/machine/include/arm/arch-armv8a.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8183"

# Trusted Firmware-A
TFA_PLAT = "mt8183"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8183"

# LK
LK_PROJECT = "mt8183-evb"

DISTRO_FEATURES_append = " optee "
