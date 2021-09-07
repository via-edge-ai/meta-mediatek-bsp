# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "ONSemiconductor firmware"

LICENSE = "Firmware-ONSemi"
LIC_FILES_CHKSUM = "file://LICENSE.pdf;md5=d10282f155f2c17fd5ce100c459c3c84"
NO_GENERIC_LICENSE[Firmware-ONSemi] = "LICENSE.pdf"

inherit allarch

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ONSemiconductor/ap1302_binaries.git;branch=main"
SRCREV = "ded0474674a7119e95d4ee8bafe21787425705e4"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_license_rename() {
	cp "${S}/AP1302 Software License Agreement.pdf" ${S}/LICENSE.pdf
}
addtask do_license_rename after do_fetch before do_populate_lic

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0330_single_fw.bin" \
		${D}${nonarch_base_libdir}/firmware
	install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0144_single_fw.bin" \
		${D}${nonarch_base_libdir}/firmware
	install "${S}/${ONSEMI_BOARD_NAME}/ap1302_ar0144_dual_fw.bin" \
		${D}${nonarch_base_libdir}/firmware
}

PACKAGES =+ "${PN}-ap1302-ar0330"
PACKAGES =+ "${PN}-ap1302-ar0144"
PACKAGES =+ "${PN}-ap1302-ar0144-dual"
FILES_${PN}-ap1302-ar0330 = "${nonarch_base_libdir}/firmware/ap1302_ar0330_single_fw.bin"
FILES_${PN}-ap1302-ar0144 = "${nonarch_base_libdir}/firmware/ap1302_ar0144_single_fw.bin"
FILES_${PN}-ap1302-ar0144-dual = "${nonarch_base_libdir}/firmware/ap1302_ar0144_dual_fw.bin"
