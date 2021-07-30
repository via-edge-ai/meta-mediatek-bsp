LICENSE = "CLOSED"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "c46914783af396365ad3187f1c5d8b86c8945743"

BUILD = "${WORKDIR}/git/${LIBDRAM_BOARD_NAME}"

require lk.inc
