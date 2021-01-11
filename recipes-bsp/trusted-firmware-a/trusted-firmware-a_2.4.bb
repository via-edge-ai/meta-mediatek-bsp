require recipes-bsp/trusted-firmware-a/trusted-firmware-a.inc
require trusted-firmware-a-mtk.inc

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware.git;name=tfa;branch=mtk-v2.4;protocol=ssh"

# Use TF-A for version
SRCREV_FORMAT = "tfa"

# TF-A v2.4
SRCREV_tfa = "9550c22641b9e2ce5430b16f0ec6a075213311a9"

LIC_FILES_CHKSUM += "file://docs/license.rst;md5=189505435dbcdcc8caa63c46fe93fa89"

# mbed TLS v2.24.0
SRC_URI_MBEDTLS = "git://github.com/ARMmbed/mbedtls.git;name=mbedtls;protocol=https;destsuffix=git/mbedtls;branch=master"
SRCREV_mbedtls = "mbedtls-2.24.0"

LIC_FILES_CHKSUM_MBEDTLS = "file://mbedtls/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

TFA_BUILD_TARGET = "bl31 fip"

inherit features_check
# optee is required because the bl2 binary requires it, and if we don't
# provide it, we won't be able to boot
REQUIRED_DISTRO_FEATURES = "optee"

do_deploy_append() {
	install -m 0644 ${S}/prebuilt/${TFA_PLATFORM}/release/bl2.img ${DEPLOYDIR}/
}
addtask do_deploy after do_install
