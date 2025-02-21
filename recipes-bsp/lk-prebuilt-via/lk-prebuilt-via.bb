DESCRIPTION = "MediaTek optee writer"
SUMMARY = "MediaTek optee writer."
LICENSE = "CLOSED"

SRC_URI = "\
	file://lk.bin \
"

FILES:${PN} ="\
	lk.bin \
"

COMPATIBLE_MACHINE = "mt*"


EXTRA_OEMAKE += "TA_DEV_KIT_DIR=${TA_DEV_KIT_DIR} \
                 CROSS_COMPILE_HOST=${HOST_PREFIX} \
                 CROSS_COMPILE_TA=${HOST_PREFIX} \
                "
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP:${PN} = "file-rdeps already-stripped"

S = "${WORKDIR}"
BUILD = "${S}"

require lk.inc

