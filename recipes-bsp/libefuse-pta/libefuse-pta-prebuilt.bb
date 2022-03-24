# Copyright (C) 2022 Ryan Cho <ryan.cho@mediatek.com>
# Released under MediatekPropritary license

SUMMARY = "MediaTek Prebuilt eFuse library"
LICENSE = "MediatekPropritary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=71cd0d8e7551828e714ab8e9c99ed74b"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

SRC_URI = "${AIOT_NDA_URI}/libefuse-pta-prebuilt.git;protocol=ssh;branch=main"
SRCREV = "43aacd39eb037f099427a7a363ed8669eadab32f"

do_install() {
        install -d ${D}${libdir}
        install -m 644 ${S}/${SOC_FAMILY}/libefuse.a ${D}${libdir}
}
