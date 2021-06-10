S = "${WORKDIR}/git"

DEPENDS = "libdram-lk"

LICENSE = "MIT & BSD & Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba5e70cccfd7c167a0ace6a1eb1d5457 \
                    file://lib/fdt/LICENSE;md5=abf7cd4d9c12ad07158318352d560fdb "

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/mtk-lk.git;protocol=ssh"
SRCREV = "865243ddc5b85e2b989da8142a6c706077e6dfd8"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI += "						\
	file://blob.h				\
"

EXTRA_OEMAKE += "LIBDRAM=${STAGING_DIR_TARGET}/${libdir}/libdram-lk.a"

do_compile () {
	oe_runmake ARCH_arm64_TOOLCHAIN_PREFIX=${TARGET_PREFIX}			\
			   CFLAGS=""											\
			   DEBUG=0												\
			   SECURE_BOOT_ENABLE=no								\
			   LIBGCC=""											\
			   GLOBAL_CFLAGS="-mstrict-align -mno-outline-atomics"	\
			   ${LK_PROJECT}
}

do_configure() {
	cp ${WORKDIR}/blob.h ${S}/include/blob.h
}

do_buildclean() {
}

inherit deploy

do_deploy () {
	install ${S}/build-${LK_PROJECT}/lk.bin ${DEPLOYDIR}/lk.bin
}
addtask do_deploy after do_install
