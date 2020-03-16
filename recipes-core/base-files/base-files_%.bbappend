FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit systemd

USB_GADGET_FUNCTION ?= "ecm"

SRC_URI_append = " \
	file://udhcpd.conf \
	file://usbgadget-net.sh \
	file://usbnet.service \
	file://usb.network \
	file://eth.network \
	file://wireless.network \
	file://mt7668.conf \
"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/init.d

	install -d ${D}${sysconfdir}/systemd/network/
	install -m 0644 ${WORKDIR}/usb.network ${D}${sysconfdir}/systemd/network/
	install -m 0644 ${WORKDIR}/eth.network ${D}${sysconfdir}/systemd/network/
	install -m 0644 ${WORKDIR}/wireless.network ${D}${sysconfdir}/systemd/network/

	install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/udhcpd.conf
	install -m 0644 ${WORKDIR}/usbnet.service ${D}${systemd_unitdir}/system/
	install -m 0755 ${WORKDIR}/usbgadget-net.sh ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/usbgadget-net.sh ${D}${systemd_unitdir}

	sed -i -e "s,@USB_GADGET_FUNCTION@,${USB_GADGET_FUNCTION},g" \
		${D}${sysconfdir}/init.d/usbgadget-net.sh
	sed -i -e "s,@USB_GADGET_FUNCTION@,${USB_GADGET_FUNCTION},g" \
		${D}${systemd_unitdir}/usbgadget-net.sh
}

do_install_append_i300-pumpkin() {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', 'sysvinit', d)}" = "sysvinit" ]; then
		install -d ${D}${sysconfdir}/modprobe.d
		install -m 0644 ${WORKDIR}/mt7668.conf ${D}${sysconfdir}/modprobe.d/
	fi
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'usbnet.service', '', d)}"
FILES_${PN} += " \
	${systemd_unitdir}/usbgadget-net.sh \
	${systemd_unitdir}/system/usbnet.service \
	${sysconfdir}/systemd/system/multi-user.target.wants/usbnet.service \
	${sysconfdir}/systemd/network/usb.network \
	${sysconfdir}/systemd/network/eth.network \
	${sysconfdir}/systemd/network/wireless.network \
	${sysconfdir}/udhcpd.conf \
	${sysconfdir}/modprobe.d/mt7668.conf \
"
