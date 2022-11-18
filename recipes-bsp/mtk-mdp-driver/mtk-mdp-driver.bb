DESCRIPTION = "Mediatek MDP Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV:mt8365 = "070e0e942593831e8483488b6d0cd7ae2e1a7fa1"
BRANCH:mt8365 = "mt8167"
TAR_PLATFORM:mt8365 = "mt8167"
REF_SYMBOLS_PATH:mt8365 = "${B}/mtk-vcu-driver/Module.symvers"

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

RPROVIDES_${PN} += "kernel-module-mtk-mdp-driver"
