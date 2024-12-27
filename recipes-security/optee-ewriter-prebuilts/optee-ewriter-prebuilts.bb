DESCRIPTION = "MediaTek optee writer"
SUMMARY = "MediaTek optee writer."
LICENSE = "CLOSED"

SRC_URI = "\
	file://usr/bin/ewriter \
	file://usr/lib/libtz_efuse.so \
"

FILES:${PN} ="\
	/usr/bin/ewriter \
	/usr/lib/libtz_efuse.so \
"

COMPATIBLE_MACHINE = "mt*"
DEPENDS:append = " optee-client"
PROVIDES = " libtz_efuse"

RPROVIDES_${PN} += " libtz_efuse.so()(64bit)"
FILES_${PN} += "${nonarch_base_libdir}/optee_armtz/"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

EXTRA_OEMAKE += "TA_DEV_KIT_DIR=${TA_DEV_KIT_DIR} \
                 CROSS_COMPILE_HOST=${HOST_PREFIX} \
                 CROSS_COMPILE_TA=${HOST_PREFIX} \
                "



INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP:${PN} = "file-rdeps already-stripped"

do_install() {
	install -D -m 777 ${WORKDIR}/usr/bin/ewriter ${D}/usr/bin/ewriter
	install -D -m 777 ${WORKDIR}/usr/lib/libtz_efuse.so ${D}/usr/lib/libtz_efuse.so
}

