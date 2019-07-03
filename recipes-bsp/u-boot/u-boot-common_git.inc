HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRCREV = "f55f7af81efd26b51f852bfe1bc5978dad948118"
SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/u-boot.git;branch=mtk-v2019.07;protocol=ssh"

PV="v2019.07+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
