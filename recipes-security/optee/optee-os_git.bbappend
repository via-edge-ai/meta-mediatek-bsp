FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PV = "3.11.0+git${SRCPV}"
SRCREV = "13fcc3349370ab5ba53f6e5c2119112cfe4a7708"
SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.11"

SRC_URI += " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"
