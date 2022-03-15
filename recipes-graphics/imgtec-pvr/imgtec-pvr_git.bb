# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Imagination Technologies Binaries"
LICENSE = "LicenseRef-MediaTek-AIoT-SLA-1-AND-Imagination-Addendum"
LIC_FILES_CHKSUM = "file://LICENSE;md5=55a3d915da1ae514a4c274329d1a31c0"

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

RPROVIDES:${PN} = " \
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
SRCREV = "8ca9c8d43ac857730dc1d03e7d79308013743695"

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

FILES:${PN} = "${libdir}/*.so* \
               ${libdir}/dri/*.so \
               ${exec_prefix}/lib/dri/*.so \
               ${nonarch_base_libdir}/firmware/rgx.fw.22.40.54.30 \
               ${nonarch_base_libdir}/firmware/rgx.sh.22.40.54.30 \
               ${exec_prefix}/local/lib/dri/*.so \
               ${datadir}/mesa/* \
               ${datadir}/drirc.d/* \
               ${sysconfdir}/*"

FILES:${PN}-dev = "${libdir}/pkgconfig/*.pc \
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
INSANE_SKIP:${PN} += "already-stripped"
