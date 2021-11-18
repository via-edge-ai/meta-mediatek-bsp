require libdram.inc

DEPENDS = "${@bb.utils.contains("SOC_FAMILY", "mt8365", "libbase-lk-prebuilt", "", d)}"

EXTRA_OEMESON:append = " -Dlk=true"

do_install:append() {
	mv ${D}/${libdir}/libdram.a ${D}/${libdir}/libdram-lk.a
}

FILES:${PN}-staticdev = "${libdir}/libdram-lk.a"
