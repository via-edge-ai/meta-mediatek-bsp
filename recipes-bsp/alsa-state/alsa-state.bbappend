MIC_CONFIG = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper-hat', 'internalmic', d)}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MIC_CONFIG}:"

SRC_URI_append := " \
	file://asound.state \
	file://asound.conf \
"
