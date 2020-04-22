S = "${WORKDIR}/git"

LICENSE = "MIT & BSD & Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba5e70cccfd7c167a0ace6a1eb1d5457 \
                    file://lib/fdt/LICENSE;md5=abf7cd4d9c12ad07158318352d560fdb \
                    file://dramk_8516/dram/LICENCE;md5=c37bc3b99197620e07a34c5375b2700a"

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/mtk-lk.git;protocol=ssh;branch=fparent/coral"
SRCREV = "00e069f0cdcad7a8321f7f22ad6fcff63f12be1a"

SRC_URI += "						\
	file://blob.h				\
"

do_compile () {
	oe_runmake ARCH_arm64_TOOLCHAIN_PREFIX=${TARGET_PREFIX}	\
			   CFLAGS=""									\
			   DEBUG=0										\
			   SECURE_BOOT_ENABLE=no						\
			   LIBGCC=""									\
			   GLOBAL_CFLAGS="-mstrict-align"				\
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
