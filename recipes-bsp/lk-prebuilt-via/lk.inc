PROVIDES = "virtual/lk"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS:append = "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', ' openssl-native mtk-secure-boot-config', '', d)}"

inherit deploy

do_deploy () {
	if [ "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
		cat ${BUILD}/lk.bin | openssl dgst -binary -sha256 > "${WORKDIR}/lk_digest"
		openssl pkeyutl -sign -inkey ${WORKDIR}/recipe-sysroot/${sysconfdir}/secure/da.pem -in "${WORKDIR}/lk_digest" \
			-out ${DEPLOYDIR}/lk.sign -pkeyopt digest:sha256 -pkeyopt \
			rsa_padding_mode:pss -pkeyopt rsa_pss_saltlen:32
		install ${BUILD}/lk.bin ${DEPLOYDIR}/lk.bin
	        install ${RECIPE_SYSROOT}/${sysconfdir}/secure/auth_sv5.auth ${DEPLOYDIR}/
	else
		install ${BUILD}/lk.bin ${DEPLOYDIR}/lk.bin
	fi
}

do_buildclean () {
	oe_runmake ARCH_arm64_TOOLCHAIN_PREFIX=${TARGET_PREFIX}			\
			   CFLAGS=""						\
			   DEBUG=0						\
			   SECURE_BOOT_ENABLE=no				\
			   LIBGCC=""						\
			   GLOBAL_CFLAGS="-mstrict-align -mno-outline-atomics"	\
			   ${LK_PROJECT} clean
}

addtask do_deploy after do_install
