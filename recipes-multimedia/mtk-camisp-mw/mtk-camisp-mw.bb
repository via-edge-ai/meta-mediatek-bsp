DESCRIPTION = "MediaTek camera hal"
LICENSE = "CLOSED"

#inherit module
COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370"

SRC_URI = "file://mtk-camisp-mw.tar.gz"
S = "${WORKDIR}/mtk-camisp-mw"

#inherit pkgconfig meson
inherit pkgconfig
inherit systemd
inherit update-rc.d

DEPENDS += "ninja-native"
DEPENDS += "glog"
DEPENDS += "gflags"
DEPENDS += "gtest"
DEPENDS += "libsync"
DEPENDS += "libutils"
DEPENDS += "libcutils"
DEPENDS += "virtual/kernel"
DEPENDS += "mtk-camisp-libcamera"
DEPENDS += "jpeg"

RDEPENDS:${PN} = " \
    gflags \
    glog \
    jpeg \
"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
ALLOW_EMPTY_${PN} = "1"

python() {
    plat = d.getVar('SOC_FAMILY', True)
    soc_dir = plat
    if plat == 'mt8370':
        soc_dir = 'mt8188'
    d.setVar('SOC_DIR', soc_dir)
}

TARGET_CFLAGS:append = " -I${STAGING_KERNEL_BUILDDIR}/${includedir} "

EXTRA_OEMESON:append = " -Dplatform=${SOC_DIR} "

TARGET_CFLAGS:append  = "-g"
BUILD_CFLAGS:append   = "-g"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

do_install() {
	cp -r ${S}/* ${D}/
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'camd.service', '', d)}"

INITSCRIPT_NAME = "camd"

TOOLCHAIN = "clang"
RUNTIME = "llvm"

FILES:${PN}-dev += "${includedir}"
FILES:${PN}-staticdev += "${libdir}"
FILES:${PN} += "${libdir}/ ${bindir} ${systemd_unitdir}/system/camd.service"
INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN}-dev += "file-rdeps"
INSANE_SKIP:${PN}-dev += "dev-elf"
INSANE_SKIP:${PN}-dev += "ldflags"
INSANE_SKIP:${PN}-staticdev += "file-rdeps"
FILES:${PN} += "/usr/share"

