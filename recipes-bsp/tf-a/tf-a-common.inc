# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Trusted Firmware-A"
HOMEPAGE = "https://github.com/ARM-software/arm-trusted-firmware"
SECTION = "bootloaders"
DEPENDS = ' \
	u-boot \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "optee-os", "", d)} \
	openssl-native \
'

PROVIDES = "virtual/tf-a"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/license.rst;md5=c709b197e22b81ede21109dbffd5f363"

inherit nopackages

B = "${WORKDIR}/build"
S = "${WORKDIR}/git"

# For now hardcode it to MT8516 since we don't support yet MT8167 differences
TFA_PLAT = "mt8516"

CFLAGS_append = " \
	-Wno-error=missing-braces \
	-Wno-error=unused-function \
	-Wno-error=unused-variable \
	-Wno-error=maybe-uninitialized \
"

EXTRA_OEMAKE = ' \
	CROSS_COMPILE=${TARGET_PREFIX} \
	PLAT=${TFA_PLAT} \
	BUILD_BASE=${B} \
	LDFLAGS= \
	BL32=${STAGING_DIR_TARGET}/lib/firmware/tee.bin \
	BL33=${STAGING_DIR_TARGET}/boot/u-boot.bin \
	NEED_BL32=${@bb.utils.contains("DISTRO_FEATURES", "optee", "yes", "no", d)} \
	NEED_BL33=yes \
	HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" \
	${@bb.utils.contains("DISTRO_FEATURES", "optee", "SPD=opteed", "", d)} \
'

do_install() {
	install -m 0644 ${B}/${TFA_PLAT}/release/fip.bin ${DEPLOY_DIR_IMAGE}/
}
