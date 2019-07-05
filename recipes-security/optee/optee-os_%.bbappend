FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

OPTEE_CORE_HEAP_SIZE ?= "1590000"

EXTRA_OEMAKE += " \
	CFG_CORE_HEAP_SIZE=${OPTEE_CORE_HEAP_SIZE} \
	PYTHONPATH=${PYTHON_SITEPACKAGES_DIR} \
"

SRC_URI += " \
	file://0001-plat-mediatek-Add-support-for-GIC.patch \
	file://0002-mediatek-add-support-for-MT8516-SoC.patch \
	file://0003-mediatek-add-support-for-MT8183-SoC.patch \
"
