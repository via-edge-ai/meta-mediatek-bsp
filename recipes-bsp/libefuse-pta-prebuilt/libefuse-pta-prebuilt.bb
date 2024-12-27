# Copyright (C) 2022 Ryan Cho <ryan.cho@mediatek.com>
# Released under MediatekPropritary license

SUMMARY = "MediaTek Prebuilt eFuse library"
LICENSE = "CLOSED"

SRC_URI = "\
	file://usr/lib/libefuse.a \
"

FILES:${PN} ="\
	/usr/lib/libefuse.a \
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
        install -d ${D}${libdir}
        install -D -m 777 ${WORKDIR}/usr/lib/libefuse.a ${D}/usr/lib/libefuse.a
}
