# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Trusted Firmware-A"
HOMEPAGE = "https://github.com/ARM-software/arm-trusted-firmware"
SECTION = "bootloaders"
DEPENDS = ' \
	virtual/bootloader \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "optee-os", "", d)} \
	openssl-native \
'

PROVIDES = "virtual/tf-a"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/docs/license.rst;md5=189505435dbcdcc8caa63c46fe93fa89"

inherit nopackages deploy

B = "${WORKDIR}/build"
S = "${WORKDIR}/git"

CFLAGS_append = " \
	-Wno-error=missing-braces \
	-Wno-error=unused-function \
	-Wno-error=unused-variable \
	-Wno-error=maybe-uninitialized \
	-Wno-error=array-bounds \
"

EXTRA_OEMAKE = ' \
	CROSS_COMPILE=${TARGET_PREFIX} \
	PLAT=${TFA_PLAT} \
	BUILD_BASE=${B} \
	LDFLAGS= \
	BL32=${STAGING_DIR_TARGET}/${nonarch_base_libdir}/firmware/tee.bin \
	BL33=${STAGING_DIR_TARGET}/boot/u-boot.bin \
	NEED_BL32=${@bb.utils.contains("DISTRO_FEATURES", "optee", "yes", "no", d)} \
	NEED_BL33=yes \
	HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "SPD=opteed", "", d)} \
'

do_deploy() {
	install -m 0644 ${B}/${TFA_PLAT}/release/fip.bin ${DEPLOYDIR}/
}
addtask do_deploy after do_install
