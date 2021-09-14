require libdram.inc

EXTRA_OEMESON:append = " -Dlk=true"

do_install:append() {
	mv ${D}/${libdir}/libdram.a ${D}/${libdir}/libdram-lk.a
}

FILES:${PN}-staticdev = "${libdir}/libdram-lk.a"
