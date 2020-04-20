# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek VPUD daemon"
LICENSE = "CLOSED"
DEPENDS = " libgcc glibc "

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit systemd
inherit update-rc.d

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/vpud.git;protocol=ssh"
SRCREV = "0cd899603229daec3eb58a665bbb7daa8e5a0175"

SRC_URI = " \
	file://vpud.service \
	file://vpud.sh \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_buildclean[noexec] = "1"


EXTRA_OEMAKE = ' \
	BINDIR=${D}${bindir} \
	LIBDIR=${D}${libdir} \
'

do_install() {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		install -d ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/mdpd.service ${D}${systemd_unitdir}/system/
	else
		install -d ${D}${sysconfdir}/init.d
		install -m 0755 ${WORKDIR}/vpud.sh ${D}${sysconfdir}/init.d/vpud
	fi
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'vpud.service', '', d)}"

INITSCRIPT_NAME = "vpud"

FILES_${PN} += " \
	${bindir}/vpud \
	${libdir}/liblic_divx.so \
	${libdir}/liblic_s263.so \
	${libdir}/libvpud_vcodec.so \
	${libdir}/libvcodec_oal.so \
	${sysconfdir}/init.d/vpud \
	${sysconfdir}/systemd/system/multi-user.target.wants/vpud.service \
	${systemd_unitdir}/system/vpud.service \
"

INSANE_SKIP_${PN} += "already-stripped"
INSANE_SKIP_${PN} += "file-rdeps"
INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN}-dev += "file-rdeps"
INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN}-dev += "ldflags"
EXCLUDE_FROM_SHLIBS = "1"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
