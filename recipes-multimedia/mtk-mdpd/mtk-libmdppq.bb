# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MDP's libmdppq library"
LICENSE = "CLOSED"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/mdp/libmdppq.git;protocol=ssh"
SRCREV = "8b250b2dfcfa9fd211f8894784f9c93cc46d5d67"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${libdir}
	install -m 0644 ${S}/libmdppq.so ${D}${libdir}
}

FILES_${PN} += "${libdir}/libmdppq.so"

INSANE_SKIP_${PN} +=" already-stripped "
INSANE_SKIP_${PN}-dev +=" dev-elf "

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
