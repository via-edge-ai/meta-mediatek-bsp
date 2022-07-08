require trusted-firmware-a-mtk_2.6.inc

PROVIDES = "virtual/bl2"

DEPENDS:append = "${@oe.utils.conditional("BL2_SIGN_ENABLE", "1", " mtk-secure-boot-tools-native mtk-secure-boot-config", "", d)}"

TFA_BUILD_TARGET = "${@bb.utils.contains('TFA_USE_PREBUILT_BL2', 'yes', '', 'bl2', d)}"

do_gen_bl2_img() {
	media="emmc"
	if [ "${@bb.utils.contains('MACHINE_FEATURES', 'ufs-boot', 'ufs-boot', '', d)}" = "ufs-boot" ]; then
		media="ufs"
	fi


	if [ "${@oe.utils.conditional('BL2_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
		cp ${B}/${TFA_PLATFORM}/release/bl2.bin ${B}/bl2.img
		python3 -m sign-image_v2.pbp \
				-i ${RECIPE_SYSROOT}/${sysconfdir}/secure/key.ini \
				-g ${RECIPE_SYSROOT}/${sysconfdir}/secure/pl_gfh_config_pss.ini \
				-func sign -o ${B}/bl2.img ${B}/bl2.img
		python3 -m secure_chip_tools.dev-info-hdr-tool emmc ${B}/bl2.img ${B}/bl2.img
		python3 -m sign-image_v2.pbp \
				-j ${EFUSE_KEY} -func keyhash_pss -o ${DEPLOYDIR}/secure/keyhash
	else
		cp ${B}/${TFA_PLATFORM}/release/bl2.bin ${B}/bl2.img.tmp
		truncate -s%4 ${B}/bl2.img.tmp
		uboot-mkimage -T mtk_image -a 0x201000 -e 0x201000 -n "media=$media;arm64=1" \
				-d ${B}/bl2.img.tmp ${B}/bl2.img
	fi
}

do_deploy() {
	if [ "x${TFA_USE_PREBUILT_BL2}" = "xyes" ]; then
		install -m 0644 ${S}/prebuilt/${TFA_BOARD_NAME}/release/bl2.img ${DEPLOYDIR}/
	else
		install -m 0644 ${B}/bl2.img ${DEPLOYDIR}/
	fi
	if [ "${@oe.utils.conditional('BL2_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
		install -m 0644 ${RECIPE_SYSROOT}/${sysconfdir}/secure/efuse.cfg ${DEPLOYDIR}/
	fi
}

python() {
    if d.getVar('TFA_USE_PREBUILT_BL2') != 'yes':
        bb.build.addtask('do_gen_bl2_img', 'do_install', 'do_compile', d)
}
