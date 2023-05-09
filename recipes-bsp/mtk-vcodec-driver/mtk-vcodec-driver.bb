DESCRIPTION = "Mediatek VCODEC Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

# MT8195: co-branch with mt8395
SRCREV:mt8195 = "5e541d8541a5f5a1992e0a06ba43d4d33a90fd08"
BRANCH:mt8195 = "mt8395"
TAR_PLATFORM:mt8195 = "mt8395"
REF_SYMBOLS_PATH:mt8195 = ""

# MT8188: co-branch with mt8395
SRCREV:mt8188 = "5e541d8541a5f5a1992e0a06ba43d4d33a90fd08"
BRANCH:mt8188 = "mt8395"
TAR_PLATFORM:mt8188 = "mt8188"
REF_SYMBOLS_PATH:mt8188 = ""

# MT8365: co-branch with mt8167
SRCREV:mt8365 = "2da06de1446655ea2b9c94039511c7b40d7978fd"
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
