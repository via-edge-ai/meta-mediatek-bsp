require optee-os-mtk.inc

DEPENDS += "${@bb.utils.contains("DISTRO_FEATURES", "optee-otp", "optee-otp", "", d)}"
do_compile[depends] += '${@bb.utils.contains("DISTRO_FEATURES", "optee-otp", "optee-otp:do_deploy", "", d)}'

OPTEE_OTP_EXTRA_OEMAKE += ' \
	CFG_RPMB_FS=y \
	CFG_RPMB_FS_DEV_ID=0 \
	${@oe.utils.conditional("OPTEE_RPMB_WRITE_KEY", "1", "CFG_RPMB_WRITE_KEY=y", "", d)} \
	${@oe.utils.conditional("OPTEE_RPMB_TEST_KEY", "1", "CFG_RPMB_TESTKEY=y", "", d)} \
'

do_compile:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'optee-otp', 'true', 'false', d)}; then
	    oe_runmake -C ${S} \
		CFG_EARLY_TA=y ${OPTEE_OTP_EXTRA_OEMAKE} \
		EARLY_TA_PATHS=${DEPLOY_DIR_IMAGE}/optee/3712bdda-569f-4940-b749-fb3b06a5fd86.stripped.elf \
		all
    fi
}

do_install:append() {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'optee-otp', 'true', 'false', d)}; then
		rm -r -f ${D}${includedir}/optee/export-user_ta/
	fi
}
