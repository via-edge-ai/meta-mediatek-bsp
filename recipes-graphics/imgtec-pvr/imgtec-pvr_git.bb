# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Imagination Technologies Binaries"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a4f7801866fbc4b8713aadccdf4da58d"

DEPENDS += "wayland libdrm"

PROVIDES = " \
	virtual/egl \
	virtual/libegl \
	virtual/libgbm \
	virtual/libgl \
	virtual/libgles2 \
	virtual/mesa \
	virtual/opencl \
"

RPROVIDES_${PN} = " \
	egl \
	libegl \
	libgbm \
	libgl \
	libgles2 \
	mesa \
	mesa-vulkan-drivers \
	opencl \
"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_RITY_URI}/libimgpvr.git;protocol=ssh;branch=main"
SRCREV = "86ef1169a350c1aec6fe393e22dd62e323890ea8"

EXTRA_OEMAKE = ' \
	IMGTEC_VERSION=${IMGTEC_VERSION} \
	IMGTEC_FW=${IMGTEC_FW} \
	IMGTEC_SH=${IMGTEC_SH} \
'

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	oe_runmake install EXEC_PREFIX=${D}${exec_prefix} LIBDIR=${D}${libdir} \
			NONARCH_BASE_LIBDIR=${D}${nonarch_base_libdir} \
			SYSCONFDIR=${D}${sysconfdir}
	rm -f ${D}${libdir}/libvulkan.so*

	if [ ! -d ${D}${exec_prefix}/lib/dri ]; then \
		install -d ${D}${exec_prefix}/lib; \
		install -d ${D}${exec_prefix}/lib/dri; \
		cp -r ${D}${exec_prefix}/lib64/dri/*.so ${D}${exec_prefix}/lib/dri/; \
	fi
}

FILES_${PN} = "${libdir}/*.so* \
               ${libdir}/dri/*.so \
               ${exec_prefix}/lib/dri/*.so \
               ${nonarch_base_libdir}/firmware/rgx.fw.22.40.54.30 \
               ${nonarch_base_libdir}/firmware/rgx.sh.22.40.54.30 \
               ${exec_prefix}/local/lib/dri/*.so \
               ${datadir}/mesa/* \
               ${datadir}/drirc.d/* \
               ${sysconfdir}/*"

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
