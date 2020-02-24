# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Imagination Technologies Binaries"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a4f7801866fbc4b8713aadccdf4da58d"

DEPENDS += "wayland"

PROVIDES = " \
	virtual/egl \
	virtual/libegl \
	virtual/libgbm \
	virtual/libgl \
	virtual/libgles2 \
	virtual/mesa \
"

RPROVIDES_${PN} = " \
	egl \
	libegl \
	libgbm \
	libgl \
	libgles2 \
	mesa \
"

S = "${WORKDIR}/git"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/imgtec-pvr.git;protocol=ssh"
SRCREV = "81f8cc23958f3a140406f9231ba2e28538d6f063"

EXTRA_OEMAKE = ' \
	IMGTEC_VERSION=${IMGTEC_VERSION} \
	IMGTEC_FW=${IMGTEC_FW} \
'

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	oe_runmake install EXEC_PREFIX=${D}${exec_prefix} LIBDIR=${D}${libdir} \
			NONARCH_BASE_LIBDIR=${D}${nonarch_base_libdir}
	rm -f ${D}/usr/lib/libwayland-egl.so*
	rm -f ${D}/usr/lib/pkgconfig/wayland-egl.pc
}

FILES_${PN} = "${libdir}/*.so* \
               ${libdir}/dri/*.so \
               ${nonarch_base_libdir}/firmware/rgx.fw.22.40.54.30 \
               ${exec_prefix}/local/lib/dri/*.so \
               ${datadir}/mesa/* \
               ${datadir}/drirc.d/*"

FILES_${PN}-dev = "${libdir}/pkgconfig/*.pc \
                   ${datadir}/pkgconfig/*.pc \
                   ${includedir}/EGL/* \
                   ${includedir}/KHR/* \
                   ${includedir}/GLES/* \
                   ${includedir}/GLES2/* \
                   ${includedir}/GLES3/* \
                   ${includedir}/CL/* \
                   ${includedir}/CL_HPP/* \
                   ${includedir}/gbm.h \
                   ${includedir}/GL"

INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} += "already-stripped"
