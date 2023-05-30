LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "0a0ee354e4bfd8e00ac3e2b4c1527d3287647855"

BUILD = "${WORKDIR}/git/${LK_BOARD_NAME}"
S = "${WORKDIR}/git"

require lk.inc
