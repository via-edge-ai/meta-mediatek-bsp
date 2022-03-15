require optee-os-mtk.inc

# Add RPMB support if necessary
OPTEE_RPMB_DEV_ID ??= "0"
MACHINE_OPTEE_EARLY_TA ??= ""

PACKAGECONFIG ??= " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee-rpmb', 'rpmb', '', d)} \
    ${@oe.utils.conditional('MACHINE_OPTEE_EARLY_TA', '', '', 'early-ta', d)} \
"

PACKAGECONFIG[rpmb] = "CFG_RPMB_FS=y·CFG_RPMB_FS_DEV_ID=${OPTEE_RPMB_DEV_ID}"
PACKAGECONFIG[rpmb-write] = "CFG_RPMB_WRITE_KEY=y"
PACKAGECONFIG[rpmb-test] = "CFG_RPMB_TESTKEY=y"
PACKAGECONFIG[early-ta] = "CFG_EARLY_TA=y EARLY_TA_DIR=${STAGING_DIR_HOST}${nonarch_base_libdir}/optee_armtz, , ${MACHINE_OPTEE_EARLY_TA}"
