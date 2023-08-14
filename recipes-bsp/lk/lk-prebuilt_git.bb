LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "c76361b2704e12d41fb9082aae03f9b68aa9e9a4"

BUILD = "${WORKDIR}/git/${LK_BOARD_NAME}"
S = "${WORKDIR}/git"

require lk.inc
