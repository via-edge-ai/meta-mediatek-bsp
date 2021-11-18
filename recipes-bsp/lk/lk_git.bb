S = "${WORKDIR}/git"
BUILD = "${WORKDIR}/git/build-${LK_PROJECT}"

DEPENDS = "libdram-lk"

LICENSE = "MIT & BSD & Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba5e70cccfd7c167a0ace6a1eb1d5457 \
                    file://lib/fdt/LICENSE;md5=abf7cd4d9c12ad07158318352d560fdb "

SRC_URI = "${AIOT_NDA_URI}/lk.git;protocol=ssh;branch=main"
SRCREV = "0552b56748fe9e57ea0362ce7085a6e2efcb5172"

EXTRA_OEMAKE += "LIBDRAM=${STAGING_DIR_TARGET}/${libdir}/libdram-lk.a"
EXTRA_OEMAKE += "LIBBASE=${STAGING_DIR_TARGET}/${libdir}/libbase-lk.a"

do_compile () {
	oe_runmake ARCH_arm64_TOOLCHAIN_PREFIX=${TARGET_PREFIX}			\
			   CFLAGS=""											\
			   DEBUG=0												\
			   SECURE_BOOT_ENABLE=no								\
			   LIBGCC=""											\
			   GLOBAL_CFLAGS="-mstrict-align -mno-outline-atomics"	\
			   ${LK_PROJECT}
}

do_configure[noexec] = "1"
do_buildclean[noexec] = "1"

require lk.inc
