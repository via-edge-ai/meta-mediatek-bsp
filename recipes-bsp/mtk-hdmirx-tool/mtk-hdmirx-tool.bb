DESCRIPTION = "Mediatek HDMIRX Tool"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

S = "${WORKDIR}/git"

BRANCH = "main"
SRCREV = "534d915c441ea39eac30c4e74d399acc82aa4b97"
SRC_URI = "git://gitlab.com/mediatek/aiot/bsp/mtk-hdmirx-tool.git;protocol=https;branch=${BRANCH}"

do_install() {
	oe_runmake \
		DESTDIR="${D}" install
}

FILES:${PN} += " \
	${bindir}/hdmirx_tool \
"