VESPER_MIC_CONFIG ??= '6_1'
MIC_CONFIG = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper-hat-6-1' if VESPER_MIC_CONFIG == '6_1' else 'vesper-hat-3-1' , 'internalmic', d)}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MIC_CONFIG}:"

SRC_URI_append := " \
	file://asound.state \
	file://asound.conf \
"
