# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MDPD service"
LICENSE = "CLOSED"
RDEPENDS_${PN} += "mtk-libmdp-prot mtk-libmdppq"
DEPENDS = "mtk-libmdp-prot mtk-libmdppq"

RPROVIDES_${PN} += "libmdpd.so()(64bit)"

inherit systemd
inherit update-rc.d

S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/mdp/mtk-mdpd.git;protocol=ssh"
SRCREV = "cae5cff3af68651f4692adb093db0ff8aa5e7403"

SRC_URI += " \
	file://mdpd.service \
	file://mdpd.sh \
"

EXTRA_OEMAKE = "mdpd libmdp.mt8167.so"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${B}/mdpd ${D}${bindir}

	install -d ${D}${libdir}
	install -m 644 ${B}/libmdpd.so ${D}${libdir}
	install -m 644 ${B}/libmdp.mt*.so ${D}${libdir}


	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "sysvinit" ]; then
		install -d ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/mdpd.service ${D}${systemd_unitdir}/system/
	else
		install -d ${D}${sysconfdir}/init.d
		install -m 0755 ${WORKDIR}/mdpd.sh ${D}${sysconfdir}/init.d/mdpd
	fi
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'mdpd.service', '', d)}"

INITSCRIPT_NAME = "mdpd"

FILES_${PN} += " \
	${bindir}/mdpd \
	${libdir}/libmdpd.so \
	${libdir}/libmdp.mt*.so \
	${sysconfdir}/init.d/mtpd \
	${sysconfdir}/systemd/system/multi-user.target.wants/mdpd.service \
	${systemd_unitdir}/system/mdpd.service \
"

INSANE_SKIP_${PN}-dev +=" dev-elf "

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
