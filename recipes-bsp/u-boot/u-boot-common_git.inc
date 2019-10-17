HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRCREV = "17b20c8dc514835647ead40cdbab9902cb2aaeae"
SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/u-boot.git;branch=mtk-v2019.10;protocol=ssh"

PV = "v2019.10+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
