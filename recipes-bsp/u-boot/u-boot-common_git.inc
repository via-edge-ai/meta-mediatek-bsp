HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRCREV = "31afd9c340a9402418792082b9feb9519321b65a"
SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/u-boot.git;branch=mtk-v2020.01;protocol=ssh"

PV = "v2020.01+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# FIXME: Drop this when migrating to Dunfell release
inherit python3native
