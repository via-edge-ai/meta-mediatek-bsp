SUMMARY = "MediaTek WiFi Test Tool"
LICENSE = "CLOSED"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/tools/wifitest.git;protocol=ssh"
SRCREV = "8bb7c2f686a71a3a112c1799cb508a11aca254ac"

PV = "git${SRCPV}"

inherit autotools

S = "${WORKDIR}/git"
