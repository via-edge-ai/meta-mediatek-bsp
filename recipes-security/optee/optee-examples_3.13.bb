# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-examples.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://0001-plugins-syslog-set-sysroot.patch \
"

SRCREV = "0607ed40746afe4cb8993149a6f275df648f7bad"
COMPATIBLE_MACHINE = "mt*"

INSANE_SKIP_${PN} += "ldflags"
