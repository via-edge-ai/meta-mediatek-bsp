FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

SRC_URI += "file://0001-mediatek-add-support-for-MT8516-SoC.patch"
