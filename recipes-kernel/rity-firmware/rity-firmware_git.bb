# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Additional firmware"
LICENSE = "CLOSED"

inherit allarch

S = "${WORKDIR}/git"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/rity-firmware.git;protocol=ssh"
SRCREV = "79da59b351705ba339a64d5418ce0ef2d0a9b2aa"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	oe_runmake install NONARCH_BASE_LIBDIR=${D}${nonarch_base_libdir}
}

PACKAGES =+ "${PN}-ap1302-ar0330"
PACKAGES =+ "${PN}-ap1302-ar0144"
FILES_${PN}-ap1302-ar0330 = "${nonarch_base_libdir}/firmware/ap1302_ar0330_single_fw.bin"
FILES_${PN}-ap1302-ar0144 = "${nonarch_base_libdir}/firmware/ap1302_ar0144_single_fw.bin"
