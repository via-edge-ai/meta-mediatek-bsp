# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-examples.inc

SRC_URI:remove = " \
	file://0001-plugins-Honour-default-cross-compiler-environment-se.patch \
	file://0002-Makefile-Enable-plugins-installation-in-rootfs.patch \
"

SRC_URI:append = " \
	file://0001-Makefile-fix-comparison-operator.patch \
"

SRCREV = "ff4b493e267d40bcf508acc300da296a3a2adac2"
COMPATIBLE_MACHINE = "mt*"

INSANE_SKIP:${PN} += "ldflags"

do_install:append() {
	mkdir -p ${D}${libdir}/tee-supplicant/plugins
	install -D -p -m0444 ${B}/plugins/* ${D}${libdir}/tee-supplicant/plugins
}

FILES:${PN} += " \
	${libdir}/tee-supplicant/plugins/ \
"
