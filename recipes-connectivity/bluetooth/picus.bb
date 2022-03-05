SUMMARY = "MediaTek Bluetooth Test Tool"
LICENSE = "CLOSED"

inherit autotools-brokensep

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_others/picus.git;protocol=ssh;branch=main;"
SRCREV = "0bb58c4b3a3e5f2017f23b3033f949c51088c045"

#PV = "git${SRCPV}"

S = "${WORKDIR}/git"

INSANE_SKIP:${PN} = "ldflags"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 picus ${D}${bindir}
}
