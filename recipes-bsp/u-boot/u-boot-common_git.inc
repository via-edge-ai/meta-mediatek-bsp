HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRCREV = "4dd0bf9236a5de4419845715886d799bebdb9ca1"
SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/u-boot.git;branch=mt8516;protocol=ssh"

PV="v2019.01+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
