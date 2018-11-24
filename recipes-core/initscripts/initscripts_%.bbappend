FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
	file://usbgadget-net.sh \
"

do_install_append() {
	install -m 0755 ${WORKDIR}/usbgadget-net.sh ${D}${sysconfdir}/init.d
}
