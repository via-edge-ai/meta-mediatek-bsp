FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PV = "3.11.0+git${SRCPV}"
SRCREV = "60b0d1ee463e0b29f986402e4451346cfda45bbe"
SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.11"

SRC_URI_append = " \
	file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
	file://0002-allow-setting-sysroot-for-clang.patch \
"
SRC_URI_remove = " \
    file://0006-allow-setting-sysroot-for-libgcc-lookup.patch \
    file://0007-allow-setting-sysroot-for-clang.patch \
"

COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"
