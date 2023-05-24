require trusted-firmware-a-mtk.inc

EXTRA_OEMAKE:append:i350-pumpkin = " \
	CFLAGS+=-DBOARD_i350_pumpkin \
"

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=ssh"
SRCREV_tfa = "7eb69a31174f4472744e7da3c29d552b2dd02af5"

SRC_URI:append:mt8390 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8395 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI += "file://rot_key.pem"
