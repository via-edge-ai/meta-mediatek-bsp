require libdram.inc

EXTRA_OEMESON_append = " -Dlk=true"

do_install_append() {
	mv ${D}/${libdir}/libdram.a ${D}/${libdir}/libdram-lk.a
}

FILES_${PN}-staticdev = "${libdir}/libdram-lk.a"
