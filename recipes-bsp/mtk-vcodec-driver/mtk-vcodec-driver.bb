DESCRIPTION = "Mediatek VCODEC Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV:mt8195 = "4baa6169b85b26c1e0b226f58b13d73dd264c9d5"
BRANCH:mt8195 = "mt8395"
TAR_PLATFORM:mt8195 = "mt8395"
REF_SYMBOLS_PATH:mt8195 = ""

SRCREV:mt8365 = "b274a95bf3ac70fc49a4862a04d0ddf9bfb44d6d"
BRANCH:mt8365 = "mt8167"
TAR_PLATFORM:mt8365 = "mt8167"
REF_SYMBOLS_PATH:mt8365 = "${B}/mtk-vcu-driver/Module.symvers"

SRC_URI += "git://gitlab.com/mediatek/aiot/bsp/mtk-vcodec-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

do_compile () {
	oe_runmake \
		TARGET_PLATFORM="${TAR_PLATFORM}" \
		EXTRA_SYMBOLS_PATH="${REF_SYMBOLS_PATH}"
}

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-vcodec-driver"
