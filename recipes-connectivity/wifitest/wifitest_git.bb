SUMMARY = "MediaTek WiFi Test Tool"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_NDA_URI}/wifitest.git;protocol=ssh;branch=main"
SRCREV = "fc1b6e9230639ecd37e75a2a4227234225495e16"

PV = "git${SRCPV}"

inherit autotools

S = "${WORKDIR}/git"
