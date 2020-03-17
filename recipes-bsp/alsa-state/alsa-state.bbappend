VESPER_MIC_CONFIG ??= '6_1'
MIC_CONFIG = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper-hat-6-1' if VESPER_MIC_CONFIG == '6_1' else 'vesper-hat-3-1' , 'internalmic', d)}"

FILESEXTRAPATHS_prepend_pumpkin := "${THISDIR}/pumpkin/${MIC_CONFIG}:"
FILESEXTRAPATHS_prepend_mt8183-evb := "${THISDIR}/mt8183-evb:"

SRC_URI_append := " \
	file://asound.state \
	file://asound.conf \
"
