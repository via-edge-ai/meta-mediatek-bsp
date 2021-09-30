require conf/machine/include/tune-cortexa73-cortexa53.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8183"

# Trusted Firmware-A
TFA_PLATFORM = "mt8183"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8183"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "mt8183-evb"

DISTRO_FEATURES:append = " optee "
DISTRO_FEATURES:remove = " x11 "

# Mali
KERNEL_MODULE_AUTOLOAD += "dma-buf-test-exporter"
MALI_SOC = "mt8183"
MALI_VERSION ??= "r32p0"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	libmali \
	linux-firmware-mt8183-scp \
"

PREFERRED_PROVIDER_virtual/egl ?= "libmali"
PREFERRED_PROVIDER_virtual/libegl ?= "libmali"
PREFERRED_PROVIDER_virtual/libgbm ?= "libmali"
PREFERRED_PROVIDER_virtual/libgl ?= "libmali"
PREFERRED_PROVIDER_virtual/libgles1 ?= "libmali"
PREFERRED_PROVIDER_virtual/libgles2 ?= "libmali"
PREFERRED_PROVIDER_virtual/mesa ?= "libmali"
PREFERRED_PROVIDER_virtual/opencl ?= "libmali"

MACHINEOVERRIDES =. "mt8385:i500:"
