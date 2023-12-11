DESCRIPTION = "Mediatek MDP Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "67e545ff126966ba5e904f6562aeb602ed1cecc3"
BRANCH = "mt8167"
TAR_PLATFORM = "mt8365"
REF_SYMBOLS_PATH = "${B}/mtk-vcu-driver-mt8365/Module.symvers"

SRC_URI += "git://gitlab.com/mediatek/aiot/bsp/mtk-mdp-driver.git;protocol=https;branch=${BRANCH} \
"

do_compile () {
	oe_runmake \
		TARGET_PLATFORM="${TAR_PLATFORM}" \
		EXTRA_SYMBOLS_PATH="${REF_SYMBOLS_PATH}"
}

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-mdp-driver-mt8365"
