DEPENDS += "python3-pycrypto-native python3-pycryptodomex-native"

PV = "3.8.0+git${SRCPV}"
SRCREV = "aa3f8997fcb2eefcb588db69a7158b51355d6985"
SRC_URI_remove = "git://github.com/OP-TEE/optee_os.git"
SRC_URI += "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.8"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"

EXTRA_OEMAKE += " \
	PYTHONPATH=${PYTHON_SITEPACKAGES_DIR} \
"
