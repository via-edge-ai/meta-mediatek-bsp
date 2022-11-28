require trusted-firmware-a-mtk.inc

EXTRA_OEMAKE:append:i350-pumpkin = " \
	CFLAGS+=-DBOARD_i350_pumpkin \
"

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=ssh"
SRCREV_tfa = "91627ed80f9265af8bfb2edb43f5137e8df5be55"
