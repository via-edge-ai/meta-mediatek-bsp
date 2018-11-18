FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit systemd

SRC_URI_append = " \
	file://udhcpd.conf \
	file://usbgadget-net.sh \
	file://usbnet.service \
"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/init.d

	install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/udhcpd.conf
	install -m 0644 ${WORKDIR}/usbnet.service ${D}${systemd_unitdir}/system/
	install -m 0755 ${WORKDIR}/usbgadget-net.sh ${D}${sysconfdir}/init.d/
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'usbnet.service', '', d)}"
FILES_${PN} += "${systemd_unitdir}/system/dhcpcd.service"
