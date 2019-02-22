FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://fw_env.config \
"

do_install_append() {
	install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/
}

FILES_${PN} += " \
	fw_env.config \
"
