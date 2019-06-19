FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
	file://interfaces \
	file://30-mt7668 \
"

do_install_append() {
	install -m 0644 ${WORKDIR}/interfaces ${D}${sysconfdir}/network/interfaces

	if [ "${MACHINE}" = "mt8516-pumpkin" ] || [ "${MACHINE}" = "mt8167-pumpkin" ]; then
		if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', 'sysvinit', d)}" = "sysvinit" ]; then
			install -m 0755 ${WORKDIR}/30-mt7668 ${D}${sysconfdir}/network/if-pre-up.d/
		fi
	fi
}

FILES_${PN} += " \
	${sysconfdir}/network/if-pre-up.d/30-mt7668 \
"
