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

BRANCH = "${DISTRO_CODENAME}"
SRC_URI = "${AIOT_RITY_URI}/libmali.git;protocol=ssh;branch=${BRANCH}"
SRCREV = "ee316b896962eb2c317e4b043b2b862b389646e6"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# default GPU don't need firmware. Handle firmware installation for specific platforms.
FIRMWARE_REQUIRE = "${@bb.utils.contains_any('MALI_SOC', 'mt8188', '1', '0', d)}"

EXTRA_OEMAKE = ' \
	SOC=${MALI_SOC} \
	MALI_VERSION=${MALI_VERSION} \
	FIRMWARE_INSTALL=${FIRMWARE_REQUIRE} \
'

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
	if [ ${FIRMWARE_REQUIRE} = 1 ]; then
		bbplain "${MALI_SOC} requires gpu firmware"
		install -d ${D}/${nonarch_base_libdir}
		install -d ${D}/${nonarch_base_libdir}/firmware
		chown -R root:root ${D}/${nonarch_base_libdir}/firmware/
		oe_runmake install LIBDIR=${D}${libdir} INCLUDEDIR=${D}${includedir} \
			DATADIR=${D}${datadir} SYSCONFDIR=${D}${sysconfdir} FIRMWAREDIR=${D}/${nonarch_base_libdir}/firmware
	else
	oe_runmake install LIBDIR=${D}${libdir} INCLUDEDIR=${D}${includedir} \
		DATADIR=${D}${datadir} SYSCONFDIR=${D}${sysconfdir}
	fi

	chown -R root:root ${D}${libdir}/
	sed -i "s,@LIBDIR@,${libdir},g" ${D}${datadir}/vulkan/icd.d/mali.json
}

FILES:${PN} = " \
	${libdir}/*.so* \
    ${datadir}/vulkan/icd.d/mali.json \
	${sysconfdir}/OpenCL/vendors/libmali.icd \
	${@bb.utils.contains('FIRMWARE_REQUIRE', '1', '${nonarch_base_libdir}/firmware/*', '', d)} \
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
