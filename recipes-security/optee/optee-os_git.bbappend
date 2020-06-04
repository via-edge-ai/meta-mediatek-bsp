PV = "3.9.0+git${SRCPV}"
SRCREV = "0e13154d74edf7451616c7bc4a25da8ae87a0a6a"
SRC_URI_remove = "git://github.com/OP-TEE/optee_os.git"
SRC_URI += "git://git@gitlab.com/baylibre/rich-iot/optee-os.git;protocol=ssh;branch=mtk-v3.9"
COMPATIBLE_MACHINE = "mt*"

OPTEEMACHINE = "${OPTEE_TARGET}"
OPTEEOUTPUTMACHINE = "mediatek"
