# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "RITY tools"
LICENSE = "CLOSED"

SRC_URI = " \
	file://rity.json \
"

inherit deploy

do_deploy() {
	install -m 0644 ${WORKDIR}/rity.json ${DEPLOYDIR}/
}
addtask do_deploy after do_install
