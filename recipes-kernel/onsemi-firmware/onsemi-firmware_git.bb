# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "ONSEMI firmware"
LICENSE = "CLOSED"

inherit allarch

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/onsemi-firmware.git;protocol=ssh;branch=main"
SRCREV = "38b4a2fc25d0e29751d694f10a8a8efeedd0732e"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	install ${S}/ap1302/ap1302_ar0330_single_fw.bin ${D}${nonarch_base_libdir}/firmware
	install ${S}/ap1302/ap1302_ar0144_single_fw.bin ${D}${nonarch_base_libdir}/firmware
	install ${S}/ap1302/ap1302_ar0144_dual_fw.bin ${D}${nonarch_base_libdir}/firmware
}

PACKAGES =+ "${PN}-ap1302-ar0330"
PACKAGES =+ "${PN}-ap1302-ar0144"
PACKAGES =+ "${PN}-ap1302-ar0144-dual"
FILES_${PN}-ap1302-ar0330 = "${nonarch_base_libdir}/firmware/ap1302_ar0330_single_fw.bin"
FILES_${PN}-ap1302-ar0144 = "${nonarch_base_libdir}/firmware/ap1302_ar0144_single_fw.bin"
FILES_${PN}-ap1302-ar0144-dual = "${nonarch_base_libdir}/firmware/ap1302_ar0144_dual_fw.bin"
