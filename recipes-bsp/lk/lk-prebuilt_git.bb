LICENSE = "CLOSED"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "6629b3f533cbf0e97f0f4ab4c20d5b1b237013e0"

BUILD = "${WORKDIR}/git/${LIBDRAM_BOARD_NAME}"

require lk.inc
