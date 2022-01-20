SUMMARY = "MediaTek Bluetooth Vendor Lib"
LICENSE = "CLOSED"

inherit autotools-brokensep

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_others/vendor_lib.git;protocol=ssh;branch=main;"
SRCREV = "c5f28d876c7adc91a98f06a9f65426d0c62986d2"


S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}"
FILES_${PN}-dev = ""
INSANE_SKIP_${PN} += "ldflags"

RPROVIDES:${PN} += " \
	bt-vendor \
"

do_install() {
    install -d ${D}${libdir}
    install -m 755 libbt-vendor.so ${D}${libdir}/
}
