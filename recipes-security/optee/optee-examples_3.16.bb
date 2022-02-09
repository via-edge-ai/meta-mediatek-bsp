# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-examples.inc

DEPENDS:append = " python3-cryptography-native "

SRC_URI:remove = " \
	file://0001-plugins-Honour-default-cross-compiler-environment-se.patch \
	file://0002-Makefile-Enable-plugins-installation-in-rootfs.patch \
"

SRC_URI:append = " \
	file://0001-Makefile-fix-comparison-operator.patch \
"

SRCREV = "65fc74309e12189ad5b6ce3ffec37c8011088a5a"
COMPATIBLE_MACHINE = "mt*"

INSANE_SKIP:${PN} += "ldflags"

do_install:append() {
	mkdir -p ${D}${libdir}/tee-supplicant/plugins
	install -D -p -m0444 ${B}/plugins/* ${D}${libdir}/tee-supplicant/plugins
}

FILES:${PN} += " \
	${libdir}/tee-supplicant/plugins/ \
"
