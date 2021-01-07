FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PV = "3.11.0+git${SRCPV}"
SRCREV = "b86ae8a3c3750294eba924a6226aaefdf0f51e39"
SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.11"

SRC_URI_append = " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
"
SRC_URI_remove = " \
    file://0006-allow-setting-sysroot-for-libgcc-lookup.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"
