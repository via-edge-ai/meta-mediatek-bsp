HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRCREV = "8229dff31bd44fb296df04f4959bc61f6a091454"
SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/u-boot.git;branch=mt8516;protocol=ssh"

PV="v2019.01+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
