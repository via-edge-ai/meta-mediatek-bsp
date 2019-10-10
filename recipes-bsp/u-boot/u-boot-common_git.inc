HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRCREV = "4c27353c766aaf1f4569508aedf5342ffee2150f"
SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/u-boot.git;branch=mtk-v2019.10;protocol=ssh"
SRCREV_mt8183 = "0dc37262a2a33db49556b3b49ec99538c0ebd9f2"
SRC_URI_mt8183 = "git://git@gitlab.com/baylibre/pumpkin/u-boot.git;branch=mtk-v2019.07-mt8183;protocol=ssh"

PV = "v2019.10+git${SRCPV}"
PV_mt8183 = "v2019.07+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
