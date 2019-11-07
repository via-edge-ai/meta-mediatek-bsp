# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MDP's libmdp_prot libaries"
LICENSE = "CLOSED"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/mdp/libmdp-prot.git;protocol=ssh"
SRCREV = "52c1ba640e58b925e7f7a5c04f5446f2d8aeb5d3"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${libdir}
	install -m 0644 ${S}/libmdp_prot.mt*.so ${D}${libdir}
}

FILES_${PN} += "${libdir}/libmdp.mt*.so"

INSANE_SKIP_${PN} +=" already-stripped "
INSANE_SKIP_${PN}-dev +=" dev-elf "

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
