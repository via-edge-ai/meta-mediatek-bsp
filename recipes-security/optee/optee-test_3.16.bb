# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-test.inc

DEPENDS:append = " python3-cryptography-native "

SRCREV = "1cf0e6d2bdd1145370033d4e182634458528579d"
COMPATIBLE_MACHINE = "mt*"

SRC_URI:remove = " \
	file://0001-host-xtest-Adjust-order-of-including-compiler.h.patch \
	file://0002-make-remove-Wno-unsafe-loop-for-clang.patch \
	file://0003-make-remove-Wmissing-noreturn-for-clang.patch \
"

do_compile:append() {
	oe_runmake test_plugin
}

do_install:append() {
	mkdir -p ${D}${libdir}/tee-supplicant/plugins
	install -D -p -m0444 ${B}/supp_plugin/*.plugin \
		${D}${libdir}/tee-supplicant/plugins/
}

FILES:${PN} += " \
	${libdir}/tee-supplicant/plugins/ \
"
