require trusted-firmware-a-mtk.inc

SRC_URI = "${AIOT_BAYLIBRE_URI}/trusted-firmware-a-mt8516.git;name=tfa;branch=mtk-v2.6-mt8516;protocol=ssh"
SRCREV_tfa = "b7f5d91f6253bda5d38635a794585ddd56799d3d"

inherit features_check
REQUIRED_MACHINE_FEATURES = "optee"
