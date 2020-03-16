FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
	file://30-mt7668 \
"

do_install_append_i300-pumpkin() {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', 'sysvinit', d)}" = "sysvinit" ]; then
		install -m 0755 ${WORKDIR}/30-mt7668 ${D}${sysconfdir}/network/if-pre-up.d/
	fi

}

FILES_${PN} += " \
	${sysconfdir}/network/if-pre-up.d/30-mt7668 \
"
