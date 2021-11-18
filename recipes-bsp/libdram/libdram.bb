require libdram.inc

DEPENDS = "${@bb.utils.contains("SOC_FAMILY", "mt8365", "libbase-prebuilt", "", d)}"
