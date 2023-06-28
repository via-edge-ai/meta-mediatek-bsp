LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "e9e54d4a2358e3aa5daf540a57603623ce9f471e"

BUILD = "${WORKDIR}/git/${LK_BOARD_NAME}"
S = "${WORKDIR}/git"

require lk.inc
