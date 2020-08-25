FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PV = "3.10.0+git${SRCPV}"
SRCREV = "3f54537acf03b0f3a2d241a4c2ed77db928a30f7"
SRC_URI_remove = "git://github.com/OP-TEE/optee_os.git"
SRC_URI += "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.10"
COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"
