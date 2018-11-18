inherit lk-image

S = "${WORKDIR}/git"

LICENSE = "MIT & BSD & Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba5e70cccfd7c167a0ace6a1eb1d5457 \
                    file://lib/fdt/LICENSE;md5=abf7cd4d9c12ad07158318352d560fdb \
                    file://dramk_8516/dram/LICENCE;md5=c37bc3b99197620e07a34c5375b2700a"

SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/mtk-lk.git;protocol=ssh;branch=pumpkin"
SRCREV = "349799fc4fa582b3ec12edfffb0b6163e3ec6ec1"

SRC_URI += "						\
	file://blob.h				\
	file://lk_key.ini			\
	file://gfh_conf.ini			\
	file://mtk-pbp-tools		\
	file://dev-info-hdr-tool.py	\
	file://root_prvk.pem		\
"

LK_PROJECT = "pumpkin8516-emmc"

PROVIDES = "virtual/bootloader"

do_compile () {
	oe_runmake ARCH_arm64_TOOLCHAIN_PREFIX=${TARGET_PREFIX}	\
			   CFLAGS=""									\
			   DEBUG=0										\
			   SECURE_BOOT_ENABLE=no						\
			   LIBGCC=""									\
			   ${LK_PROJECT}
}

do_gen_image() {
	cp ${S}/build-${LK_PROJECT}/lk.bin ${WORKDIR}/lk.img.tmp
	python ${WORKDIR}/mtk-pbp-tools/pbp.py -g ${WORKDIR}/gfh_conf.ini \
		   -i ${WORKDIR}/lk_key.ini -func sign \
		   -o ${WORKDIR}/lk.img.tmp ${WORKDIR}/lk.img.tmp
	python ${WORKDIR}/dev-info-hdr-tool.py emmc ${WORKDIR}/lk.img.tmp \
												${WORKDIR}/lk.img
}

do_configure() {
	cp ${WORKDIR}/blob.h ${S}/include/blob.h
}

do_buildclean() {
}

addtask do_gen_image before do_install after do_compile

do_install () {
	install ${S}/build-${LK_PROJECT}/lk.bin ${DEPLOY_DIR_IMAGE}/lk.bin
	install ${WORKDIR}/lk.img ${DEPLOY_DIR_IMAGE}/lk.img
}
