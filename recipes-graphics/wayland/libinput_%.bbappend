FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/common:"
FILESEXTRAPATHS_prepend_i500-pumpkin := "${THISDIR}/${PN}/i500-pumpkin:"

SRC_URI += " \
	file://60-libinput.rules \
"

do_install_append() {
	install -D -p -m0644 ${WORKDIR}/60-libinput.rules \
		${D}${sysconfdir}/udev/rules.d/60-libinput.rules
}
