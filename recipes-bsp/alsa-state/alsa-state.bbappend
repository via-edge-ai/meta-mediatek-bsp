VESPER_MIC_CONFIG ??= '3_1'

INTERNAL_OR_PIN_HEADER = "${@bb.utils.contains('I300_PUMPKIN_AUDIO_CONF', 'i2s', '40-pins-header' , 'internalmic', d)}"
MIC_CONFIG = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper-hat-6-1' if VESPER_MIC_CONFIG == '6_1' else 'vesper-hat-3-1', '${INTERNAL_OR_PIN_HEADER}', d)}"

FILESEXTRAPATHS_prepend_i300-pumpkin := "${THISDIR}/pumpkin/${MIC_CONFIG}:"
FILESEXTRAPATHS_prepend_i300a-sb30 := "${THISDIR}/pumpkin/${MIC_CONFIG}:"
FILESEXTRAPATHS_prepend_mt8183-evb := "${THISDIR}/mt8183-evb:"
FILESEXTRAPATHS_prepend_mt8183-pumpkin := "${THISDIR}/mt8183-pumpkin:"
