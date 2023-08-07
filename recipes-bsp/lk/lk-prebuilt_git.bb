LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "b842a6ae8527cb7e93b61935913f30ce21584962"

BUILD = "${WORKDIR}/git/${LK_BOARD_NAME}"
S = "${WORKDIR}/git"

require lk.inc
