DESCRIPTION = "Mediatek VCU-MT8395 Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

COMPATIBLE_MACHINE = "i1200-demo"

inherit module

SRCREV = "e6317eaf580d38c6519cb4eff4da33378e799a09"

BRANCH = "mt8395"

SRC_URI += "git://gitlab.com/mediatek/aiot/bsp/mtk-vcu-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-vcu-mt8395"
