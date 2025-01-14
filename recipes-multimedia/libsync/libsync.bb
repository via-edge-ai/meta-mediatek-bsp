SUMMARY = "LIBSYNC"
DESCRIPTION = "LIBSYNC"

#FILESEXTRAPATHS_append := ":${THISDIR}/files"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=c1a3ff0b97f199c7ebcfdd4d3fed238e"

SRC_URI = " git://android.googlesource.com/platform/system/core;protocol=https \
		file://0001-add-makefile-for-libsync.patch \
		file://0002-replace-strlcpy-with-strncpy.patch \
"

SRCREV = "20ac1203a3201ac3e6d05a19325f5569033f3d08"

S = "${WORKDIR}/git"

do_compile() {
	cd ${S}/libsync && make
}

do_install() {
	cd ${S}/libsync && make install DESTDIR="${D}" INCDIR="${includedir}" \
	SRCDIR="${S}" LIBDIR="${libdir}"
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
ALLOW_EMPTY_${PN} = "1"
FILES_${PN} += "${libdir}"
FILES_${PN}-dev += "${includedir}"
INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN}-dev += "file-rdeps"
INSANE_SKIP:${PN}-dev += "dev-elf"
INSANE_SKIP:${PN}-dev += "ldflags"

INSANE_SKIP_${PN} += "ldflags"

