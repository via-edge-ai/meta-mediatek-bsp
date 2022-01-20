SUMMARY = "MediaTek Bluetooth Test Tool"
LICENSE = "CLOSED"

inherit autotools-brokensep

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_others/boots.git;protocol=ssh;branch=main;"
SRCREV = "7886d78ab18f5f2c7e0611a014ffe0e2b5db5570"


S = "${WORKDIR}/git"

INSANE_SKIP_${PN} = "ldflags"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 boots ${D}${bindir}
    install -m 0755 boots_srv ${D}${bindir}
}
