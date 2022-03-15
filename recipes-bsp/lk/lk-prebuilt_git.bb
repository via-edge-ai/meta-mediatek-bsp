LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6fcd7dfec853b4eca3b44877b51c0943"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "a1a7253bf99cc66af9efc8237a9300514aab078b"

BUILD = "${WORKDIR}/git/${LIBDRAM_BOARD_NAME}"

require lk.inc
