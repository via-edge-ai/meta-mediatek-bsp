require conf/machine/include/tune-cortexa35.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8516"

# Trusted Firmware-A
TFA_PLAT = "mt8516"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8516"

USE_VT = "0"

DISTRO_FEATURES_append = " optee "

MACHINEOVERRIDES =. "mt8362:mt8362b:i300:i300b:"
