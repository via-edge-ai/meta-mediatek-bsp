LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c25f59288708e3fd9961c9e6142aafee"

SRC_URI = "${AIOT_RITY_URI}/lk-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "fde5d04761946fae4634f3549a2be550ed8d7209"

BUILD = "${WORKDIR}/git/${LIBDRAM_BOARD_NAME}"
S = "${WORKDIR}/git"

require lk.inc
