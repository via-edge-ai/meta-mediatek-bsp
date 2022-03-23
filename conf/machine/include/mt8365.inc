require conf/machine/include/arm/armv8a/tune-cortexa53.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8365"

# Trusted Firmware-A
TFA_PLATFORM = "mt8365"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8175"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8365-pumpkin"

MACHINE_FEATURES:append = " optee"
DISTRO_FEATURES:remove = " x11 "

# Mali
KERNEL_MODULE_AUTOLOAD += "dma-buf-test-exporter"
MALI_SOC = "mt8365"
MALI_VERSION ??= "r32p0"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	libmali \
"

PREFERRED_PROVIDER_virtual/egl ?= "libmali"
PREFERRED_PROVIDER_virtual/libegl ?= "libmali"
PREFERRED_PROVIDER_virtual/libgbm ?= "libmali"
PREFERRED_PROVIDER_virtual/libgl ?= "libmali"
PREFERRED_PROVIDER_virtual/libgles1 ?= "libmali"
PREFERRED_PROVIDER_virtual/libgles2 ?= "libmali"
PREFERRED_PROVIDER_virtual/mesa ?= "libmali"
PREFERRED_PROVIDER_virtual/opencl ?= "libmali"

PREFERRED_RPROVIDER_virtual/mdpd ??= "${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "mtk-mdpd", "mdp-prebuilt", d)}"

MACHINEOVERRIDES =. "i350:"
