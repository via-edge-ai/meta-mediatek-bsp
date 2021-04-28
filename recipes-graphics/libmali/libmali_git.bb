# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "ARM libmali"
LICENSE = "CLOSED"

inherit features_check

CONFLICT_DISTRO_FEATURES = "x11 vulkan"

DEPENDS += "libdrm wayland"

PROVIDES = " \
	virtual/egl \
	virtual/libegl \
	virtual/libgbm \
	virtual/libgl \
	virtual/libgles1 \
	virtual/libgles2 \
	virtual/mesa \
	virtual/opencl \
"

RPROVIDES_${PN} = " \
	egl \
	libegl \
	libgbm \
	libgl \
	libgles1 \
	libgles2 \
	mesa \
	opencl \
"

S = "${WORKDIR}/git"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/libmali.git;protocol=ssh"
SRCREV = "618acf37d1acab4844be5af66071c1d2567153fc"

EXTRA_OEMAKE = ' \
	SOC=${MALI_SOC} \
	MALI_VERSION=${MALI_VERSION} \
'

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	oe_runmake install BINDIR=${D}${bindir} LIBDIR=${D}${libdir} \
		INCLUDEDIR=${D}${includedir}
	chown -R root:root ${D}${libdir}/
}

PACKAGES =+ "${PN}-tests"

FILES_${PN} = "${libdir}/*.so*"
FILES_${PN}-tests = "${bindir}/*"
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
