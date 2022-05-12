# Copyright (C) 2020 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "ARM libmali"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=E1263579487EF4E8EA1FCF0D282A6531"
LICENSE_FLAGS = "commercial"

inherit features_check

CONFLICT_DISTRO_FEATURES = "x11"

DEPENDS += "libdrm wayland"
DEPENDS += "${@bb.utils.contains("DISTRO_FEATURES", "vulkan", "vulkan-loader", "", d)}"

PROVIDES = " \
	virtual/egl \
	virtual/libegl \
	virtual/libgbm \
	virtual/libgl \
	virtual/libgles1 \
	virtual/libgles2 \
	virtual/mesa \
"

RPROVIDES:${PN} = " \
	egl \
	libegl \
	libgbm \
	libgl \
	libgles1 \
	libgles2 \
	mesa \
	mesa-vulkan-drivers \
"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_RITY_URI}/libmali.git;protocol=ssh;branch=main"
SRCREV = "11c92e03430a4346e3b8982a7a992441e570789e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE = ' \
	SOC=${MALI_SOC} \
	MALI_VERSION=${MALI_VERSION} \
'

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	oe_runmake install LIBDIR=${D}${libdir} INCLUDEDIR=${D}${includedir} \
		DATADIR=${D}${datadir} SYSCONFDIR=${D}${sysconfdir}
	chown -R root:root ${D}${libdir}/
	sed -i "s,@LIBDIR@,${libdir},g" ${D}${datadir}/vulkan/icd.d/mali.json
}

FILES:${PN} = " \
	${libdir}/*.so* \
    ${datadir}/vulkan/icd.d/mali.json \
	${sysconfdir}/OpenCL/vendors/libmali.icd \
"
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
