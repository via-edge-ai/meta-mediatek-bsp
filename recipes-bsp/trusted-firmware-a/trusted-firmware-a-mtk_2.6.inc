require trusted-firmware-a-mtk.inc

EXTRA_OEMAKE:append:i350-pumpkin = " \
	CFLAGS+=-DBOARD_i350_pumpkin \
"

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=ssh"
SRCREV_tfa = "fe896879289bee2d0b28a19d928f93597e73cc47"

SRC_URI:append:mt8370 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8390 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8395 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI += "file://rot_key.pem"
