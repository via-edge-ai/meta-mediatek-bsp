LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6fcd7dfec853b4eca3b44877b51c0943"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "cd7db570681cfef2573d6a29a4494ed5000109dd"

BUILD = "${WORKDIR}/git/${LIBDRAM_BOARD_NAME}"
S = "${WORKDIR}/git"

require lk.inc
