VESPER_MIC_CONFIG ??= '3_1'
MIC_CONFIG = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'vesper-hat-6-1' if VESPER_MIC_CONFIG == '6_1' else 'vesper-hat-3-1' , 'internalmic', d)}"

FILESEXTRAPATHS_prepend_i300-pumpkin := "${THISDIR}/pumpkin/${MIC_CONFIG}:"
FILESEXTRAPATHS_prepend_i300a-sb30 := "${THISDIR}/pumpkin/${MIC_CONFIG}:"
FILESEXTRAPATHS_prepend_mt8183-evb := "${THISDIR}/mt8183-evb:"
FILESEXTRAPATHS_prepend_mt8183-pumpkin := "${THISDIR}/mt8183-evb:"
