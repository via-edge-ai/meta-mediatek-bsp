require conf/machine/include/tune-cortexa53.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8365"

# Trusted Firmware-A
TFA_PLAT = "mt8365"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8365"

# LK
LK_PROJECT = "mt8365-evb"

DISTRO_FEATURES_append = " optee "

MACHINEOVERRIDES =. "mt8168:mt8175:i500:"
