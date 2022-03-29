FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

INTERNAL_OR_PIN_HEADER = "${@bb.utils.contains('I300_PUMPKIN_AUDIO_CONF', 'i2s', '40-pins-header' , 'internalmic', d)}"
MYCONF = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper' , '${INTERNAL_OR_PIN_HEADER}', d)}"

SRC_URI:append = " \
	file://client_${MYCONF}.conf \
	file://default_${MYCONF}.pa \
"

do_install:append:i300-pumpkin() {
	install -d ${D}${sysconfdir}/pulse
	install -m 0644 ${WORKDIR}/client_${MYCONF}.conf ${D}${sysconfdir}/pulse/client.conf
	install -m 0644 ${WORKDIR}/default_${MYCONF}.pa ${D}${sysconfdir}/pulse/default.pa
}

