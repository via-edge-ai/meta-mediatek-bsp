FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append := " \
	file://asound.state \
	file://asound.conf \
"
