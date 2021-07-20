# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-examples.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://0001-plugins-syslog-set-sysroot.patch \
"

SRC_URI_remove = " \
	file://0001-make-Pass-ldflags-during-link.patch \
"

SRCREV = "e9c870525af8f7e7fccf575a0ca5394ce55adcec"
COMPATIBLE_MACHINE = "mt*"

INSANE_SKIP_${PN} += "ldflags"
