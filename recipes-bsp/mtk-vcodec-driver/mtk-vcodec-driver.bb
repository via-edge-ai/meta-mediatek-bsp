DESCRIPTION = "Mediatek VCODEC Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

# MT8195: co-branch with mt8395
SRCREV:mt8195 = "a03d41454a696c4b1b8d3e4279ab8579816bfccf"
BRANCH:mt8195 = "mt8395"
TAR_PLATFORM:mt8195 = "mt8395"
REF_SYMBOLS_PATH:mt8195 = ""

# MT8188: co-branch with mt8395
SRCREV:mt8188 = "a03d41454a696c4b1b8d3e4279ab8579816bfccf"
BRANCH:mt8188 = "mt8395"
TAR_PLATFORM:mt8188 = "mt8188"
REF_SYMBOLS_PATH:mt8188 = ""

# MT8365: co-branch with mt8167
SRCREV:mt8365 = "3aa8f18d9e49b09a8badd0a1be227782079b1767"
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
