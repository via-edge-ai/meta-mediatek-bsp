# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Imagination Technologies Binaries"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a4f7801866fbc4b8713aadccdf4da58d"

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
SRCREV = "3d245cacdcd8840a75b73a251e7c728331b8fe43"

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
	rm ${D}${libdir}/libwayland-egl.so*
	rm ${D}${libdir}/pkgconfig/wayland-egl.pc
}

FILES_${PN} = "${libdir}/libGLESv2.so* \
               ${libdir}/libGLESv1_CM_PVR_MESA.so* \
               ${libdir}/libGLESv2_PVR_MESA.so* \
               ${libdir}/libglslcompiler.so* \
               ${libdir}/libpvr_dri_support.so* \
               ${libdir}/libsrv_um.so* \
               ${libdir}/libusc.so* \
               ${libdir}/liboclcompiler.so* \
               ${libdir}/libPVROCL.so* \
               ${libdir}/libglapi.so* \
               ${libdir}/libGLESv1_CM.so* \
               ${libdir}/libEGL.so* \
               ${libdir}/libgbm.so* \
               ${libdir}/libufwriter.so \
               ${libdir}/libPVRScopeServices.so \
               ${nonarch_base_libdir}/firmware/rgx.fw.22.40.54.30 \
               ${exec_prefix}/local/lib/dri/pvr_dri.so"

FILES_${PN}-dev = "${libdir}/pkgconfig/egl.pc \
                   ${libdir}/pkgconfig/gbm.pc \
                   ${libdir}/pkgconfig/glesv2.pc  \
                   ${libdir}/pkgconfig/glesv1_cm.pc \
                   ${includedir}/EGL/* \
                   ${includedir}/KHR/* \
                   ${includedir}/GLES/* \
                   ${includedir}/GLES2/* \
                   ${includedir}/GLES3/* \
                   ${includedir}/CL/* \
                   ${includedir}/CL_HPP/* \
                   ${includedir}/gbm.h"

INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} += "already-stripped"
