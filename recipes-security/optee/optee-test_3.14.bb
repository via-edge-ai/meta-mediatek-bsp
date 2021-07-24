# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-test.inc

SRCREV = "f2eb88affbb7f028561b4fd5cbd049d5d704f741"
COMPATIBLE_MACHINE = "mt*"

SRC_URI_remove = " \
	file://0001-host-xtest-Adjust-order-of-including-compiler.h.patch \
	file://0002-make-remove-Wno-unsafe-loop-for-clang.patch \
	file://0003-make-remove-Wmissing-noreturn-for-clang.patch \
	file://run-ptest \
"

do_compile_append() {
	oe_runmake test_plugin
}

do_install_append() {
	mkdir -p ${D}${libdir}/tee-supplicant/plugins
	install -D -p -m0444 ${B}/supp_plugin/*.plugin \
		${D}${libdir}/tee-supplicant/plugins/
}

FILES_${PN} += " \
	${libdir}/tee-supplicant/plugins/ \
"
