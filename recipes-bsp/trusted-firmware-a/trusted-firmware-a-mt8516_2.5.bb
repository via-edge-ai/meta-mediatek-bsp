require trusted-firmware-a-mtk.inc

SRC_URI = "${AIOT_URI}/device/mt8516/trusted-firmware-a.git;name=tfa;branch=mtk-v2.5-mt8516;protocol=ssh"
SRCREV_tfa = "e20cdc51cd2749af32bd0f2da950fe4d631d4bbe"

TFA_USE_PREBUILT_BL2 = "no"

inherit features_check
REQUIRED_DISTRO_FEATURES = "optee"
