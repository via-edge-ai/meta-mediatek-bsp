# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require mdp-prebuilt.inc

PACKAGES =. " \
	libmdppq \
	libmdp-prot \
"
PROVIDES =. " \
	libmdppq \
	libmdp-prot \
"

do_install() {
	install -d ${D}${libdir}
	install -m 0644 ${S}/libmdppq/libmdppq.so ${D}${libdir}
	install -m 0644 ${S}/libmdp-prot/libmdp_prot.mt*.so ${D}${libdir}
}

FILES_libmdppq += "${libdir}/libmdppq.so"
FILES_libmdp-prot += "${libdir}/libmdp_prot.mt*.so"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
