DESCRIPTION = "Mediatek VCU Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

# MT8195: co-branch with mt8395
SRCREV:mt8195 = "a5cf85327cfe4a8195391869455316d14eb79231"
BRANCH:mt8195 = "mt8395"
TAR_PLATFORM:mt8195 = "mt8395"
REF_SYMBOLS_PATH:mt8195 = "${B}/mtk-vcodec-driver/Module.symvers"

# MT8188: co-branch with mt8395
SRCREV:mt8188 = "a5cf85327cfe4a8195391869455316d14eb79231"
BRANCH:mt8188 = "mt8395"
TAR_PLATFORM:mt8188 = "mt8188"
REF_SYMBOLS_PATH:mt8188 = "${B}/mtk-vcodec-driver/Module.symvers"

# MT8365: co-branch with mt8167
SRCREV:mt8365 = "737b2f4a50f6d9ddf6e74e2527f98624e6b9f600"
BRANCH:mt8365 = "mt8167"
TAR_PLATFORM:mt8365 = "mt8167"
REF_SYMBOLS_PATH:mt8365 = ""

SRC_URI += "git://gitlab.com/mediatek/aiot/bsp/mtk-vcu-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

do_compile () {
	oe_runmake \
		TARGET_PLATFORM="${TAR_PLATFORM}" \
		EXTRA_SYMBOLS_PATH="${REF_SYMBOLS_PATH}"
}

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-vcu-driver"
