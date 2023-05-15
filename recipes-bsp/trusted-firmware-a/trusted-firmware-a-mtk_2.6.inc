require trusted-firmware-a-mtk.inc

EXTRA_OEMAKE:append:i350-pumpkin = " \
	CFLAGS+=-DBOARD_i350_pumpkin \
"

SRC_URI = "${AIOT_BSP_URI}/trusted-firmware-a.git;name=tfa;branch=mtk-v2.6;protocol=ssh"
SRCREV_tfa = "8c345efc7333124c85323bc45af08714ac7b714c"

SRC_URI:append:mt8390 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI:append:mt8395 = " file://0001-tf-a-auth-add-support-for-HW-crypto.patch "
SRC_URI += "file://rot_key.pem"
