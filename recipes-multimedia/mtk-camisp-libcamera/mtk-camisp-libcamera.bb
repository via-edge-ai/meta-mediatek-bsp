DESCRIPTION = "MediaTek Camera ISP Prebuilt libcamera"
LICENSE = "CLOSED"

#inherit module
COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370"

S:mt8395 = "${WORKDIR}/mt8395"
SRC_URI:mt8395 = "file://mt8395.tar.gz"

S:mt8390 = "${WORKDIR}/mt8390"
SRC_URI:mt8390 = "file://mt8390.tar.gz"

S:mt8370 = "${WORKDIR}/mt8390"
SRC_URI:mt8370 = "file://mt8390.tar.gz"

#inherit pkgconfig meson
do_install() {
	install -D "${S}/arm64/clang"/* -t "${D}${libdir}"

	install -d ${D}${includedir}/aaa_ae
	install -m 644 ${S}/include/aaa_ae/*.h ${D}${includedir}/aaa_ae

	install -d ${D}${includedir}/aaa_ae_pipe
	install -m 644 ${S}/include/aaa_ae_pipe/*.h ${D}${includedir}/aaa_ae_pipe

	install -d ${D}${includedir}/aaa_af
	install -m 644 ${S}/include/aaa_af/*.h ${D}${includedir}/aaa_af

	install -d ${D}${includedir}/aaa_awb
	install -m 644 ${S}/include/aaa_awb/*.h ${D}${includedir}/aaa_awb

	install -d ${D}${includedir}/aaa_flicker
	install -m 644 ${S}/include/aaa_flicker/*.h ${D}${includedir}/aaa_flicker

	install -d ${D}${includedir}/aaa_gma
	install -m 644 ${S}/include/aaa_gma/*.h ${D}${includedir}/aaa_gma

	install -d ${D}${includedir}/aaa_hlr
	install -m 644 ${S}/include/aaa_hlr/*.h ${D}${includedir}/aaa_hlr

	install -d ${D}${includedir}/aaa_ltm
	install -m 644 ${S}/include/aaa_ltm/*.h ${D}${includedir}/aaa_ltm

	install -d ${D}${includedir}/aaa_stat
	install -m 644 ${S}/include/aaa_stat/*.h ${D}${includedir}/aaa_stat

	install -d ${D}${includedir}/aaa_tnc
	install -m 644 ${S}/include/aaa_tnc/*.h ${D}${includedir}/aaa_tnc

	install -d ${D}${includedir}/aaa_toneutil
	install -m 644 ${S}/include/aaa_toneutil/*.h ${D}${includedir}/aaa_toneutil

	install -d ${D}${includedir}/libcac
	install -m 644 ${S}/include/libcac/*.h ${D}${includedir}/libcac

	install -d ${D}${includedir}/libfus
	install -m 644 ${S}/include/libfus/*.h ${D}${includedir}/libfus

	install -d ${D}${includedir}/liblsc
	install -m 644 ${S}/include/liblsc/*.h ${D}${includedir}/liblsc

	install -d ${D}${includedir}/libmcnr
	install -m 644 ${S}/include/libmcnr/*.h ${D}${includedir}/libmcnr

	install -d ${D}${includedir}/libplatform
	install -m 644 ${S}/include/libplatform/*.h ${D}${includedir}/libplatform
}

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "file-rdeps"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = "${libdir}/*.so ${bindir}"
