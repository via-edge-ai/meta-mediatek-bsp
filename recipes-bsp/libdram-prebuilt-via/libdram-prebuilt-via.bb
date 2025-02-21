DESCRIPTION = "MediaTek optee writer"
SUMMARY = "MediaTek optee writer."
LICENSE = "CLOSED"

SRC_URI = "\
	file://usr/lib/libdram.a \
"

FILES:${PN} ="\
	/usr/lib/libdram.a \
"

COMPATIBLE_MACHINE = "mt*"

SOLIBS = ".a"
FILES_SOLIBSDEV = ""

EXTRA_OEMAKE += "TA_DEV_KIT_DIR=${TA_DEV_KIT_DIR} \
                 CROSS_COMPILE_HOST=${HOST_PREFIX} \
                 CROSS_COMPILE_TA=${HOST_PREFIX} \
                "



INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP:${PN} = "file-rdeps already-stripped"

do_install() {
	install -D -m 777 ${WORKDIR}/usr/lib/libdram.a ${D}/usr/lib/libdram.a
}

