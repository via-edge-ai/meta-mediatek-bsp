FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-sys-v4l2-add-support-for-MT21-format.patch \
	file://0001-v4l2videoenc-set-caps-with-prepend-header-flag.patch \
"
