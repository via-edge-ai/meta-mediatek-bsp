require trusted-firmware-a-mtk.inc

EXTRA_OEMAKE:append:i350-pumpkin = " \
	CFLAGS+=-DBOARD_i350_pumpkin \
"

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.5;protocol=ssh"
SRCREV_tfa = "fb3138be38a5fa2f6ac02f28e1c9c3b0670bcadf"
