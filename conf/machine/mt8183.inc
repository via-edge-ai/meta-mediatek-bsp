require conf/machine/include/tune-cortexa73-cortexa53.inc
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
DISTRO_FEATURES_remove = " x11 vulkan "

# Mali
MALI_GEN = "bifrost"
MALI_VERSION ??= "r20p0"

MACHINE_EXTRA_RRECOMMENDS_append = " \
	libmali \
"

PREFERRED_PROVIDER_virtual/egl ?= "libmali"
PREFERRED_PROVIDER_virtual/libegl ?= "libmali"
PREFERRED_PROVIDER_virtual/libgbm ?= "libmali"
PREFERRED_PROVIDER_virtual/libgl ?= "libmali"
PREFERRED_PROVIDER_virtual/libgles1 ?= "libmali"
PREFERRED_PROVIDER_virtual/libgles2 ?= "libmali"
PREFERRED_PROVIDER_virtual/mesa ?= "libmali"

PREFERRED_VERSION_weston = "4.0%"

MACHINEOVERRIDES =. "mt8385:i500:"
