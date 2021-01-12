require conf/machine/include/tune-cortexa53.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8175"

# Trusted Firmware-A
TFA_PLATFORM = "mt8175"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8175"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8175-evb"

DISTRO_FEATURES_append = " optee "

MACHINEOVERRIDES =. "mt8168:mt8365:i500:"
