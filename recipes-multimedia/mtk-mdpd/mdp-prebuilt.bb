# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek pre-built binaries for MDP"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_RITY_URI}/mdp-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "f4e02bea149c8a536756d561afaae9e3b50f3977"

PACKAGES =. " \
	libmdppq \
	libmdp-prot \
"
PROVIDES =. " \
	libmdppq \
	libmdp-prot \
"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${libdir}
	install -m 0644 ${S}/libmdppq/libmdppq.so ${D}${libdir}
	install -m 0644 ${S}/libmdp-prot/libmdp_prot.mt*.so ${D}${libdir}
}

FILES_libmdppq += "${libdir}/libmdppq.so"
FILES_libmdp-prot += "${libdir}/libmdp_prot.mt*.so"

INSANE_SKIP_${PN} += " already-stripped "
INSANE_SKIP_${PN}-dev += " dev-elf "

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
