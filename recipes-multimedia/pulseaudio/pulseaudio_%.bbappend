FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

MYCONF = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper', 'internalmic', d)}"

SRC_URI_append = " \
	file://client_${MYCONF}.conf \
	file://default_${MYCONF}.pa \
"

do_install_append() {
	install -d ${D}${sysconfdir}/pulse
	install -m 0644 ${WORKDIR}/client_${MYCONF}.conf ${D}${sysconfdir}/pulse/client.conf
	install -m 0644 ${WORKDIR}/default_${MYCONF}.pa ${D}${sysconfdir}/pulse/default.pa
}

