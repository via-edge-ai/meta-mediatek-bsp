FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

EXTRA_OEMAKE += " \
	PYTHONPATH=${PYTHON_SITEPACKAGES_DIR} \
"

SRC_URI += " \
	file://0001-plat-mediatek-Add-support-for-GIC.patch \
	file://0002-mediatek-add-support-for-MT8516-SoC.patch \
	file://0003-mediatek-add-support-for-MT8183-SoC.patch \
"
