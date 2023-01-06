require trusted-firmware-a-mtk.inc

EXTRA_OEMAKE:append:i350-pumpkin = " \
	CFLAGS+=-DBOARD_i350_pumpkin \
"

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=ssh"
SRCREV_tfa = "ff1691b0b53bd7aef235115c2e3fe82fb6265179"

SRC_URI += "file://rot_key.pem"
