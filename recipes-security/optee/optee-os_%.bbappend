FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

OPTEEMACHINE = "mediatek-mt8516"
OPTEEOUTPUTMACHINE = "mediatek"

SRC_URI += "file://0001-mediatek-add-support-for-MT8516-SoC.patch"
