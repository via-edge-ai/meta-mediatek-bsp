require conf/machine/include/tune-cortexa35.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8167"

# Trusted Firmware-A
TFA_PLATFORM = "mt8516"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8516"
OPTEE_DRAM_SIZE = "0x80000000"

# LK
LK_PROJECT = "pumpkin8516-emmc"

DISTRO_FEATURES_append = " optee "
DISTRO_FEATURES_remove = " x11 "

IMGTEC_VERSION ??= "1.13@5776728"
IMGTEC_FW ??= "rgx.fw.22.40.54.30"
IMGTEC_SH ??= "rgx.sh.22.40.54.30"

MACHINE_EXTRA_RRECOMMENDS_append = " \
	imgtec-pvr \
"

PREFERRED_PROVIDER_virtual/egl ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libegl ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libgbm ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libgl ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libgles2 ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/mesa ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/opencl ?= "imgtec-pvr"

MACHINEOVERRIDES =. "mt8362:mt8362a:i300:i300a:"
