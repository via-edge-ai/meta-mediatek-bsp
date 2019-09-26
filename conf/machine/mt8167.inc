require conf/machine/include/tune-cortexa35.inc
require conf/machine/include/soc-family.inc
require mediatek-common.inc

SOC_FAMILY = "mt8167"

# Trusted Firmware-A
TFA_PLAT = "mt8516"

# OP-TEE
OPTEE_TARGET = "mediatek-mt8516"

# LK
LK_PROJECT = "pumpkin8516-emmc"

DISTRO_FEATURES_append = " optee "

IMGTEC_VERSION ??= "1.9ED4917962"
IMGTEC_FW ??= "rgx.fw.22.40.54.30"

MACHINE_EXTRA_RRECOMMENDS_append = " \
	imgtec-pvr \
"

PREFERRED_PROVIDER_virtual/egl ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libegl ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libgbm ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libgl ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/libgles2 ?= "imgtec-pvr"
PREFERRED_PROVIDER_virtual/mesa ?= "imgtec-pvr"
PREFERRED_VERSION_weston = "4.0%"
