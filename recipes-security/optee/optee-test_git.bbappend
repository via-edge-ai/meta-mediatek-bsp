FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
COMPATIBLE_MACHINE = "mt*"

SRC_URI_append = "\
	file://0001-HACK-fix-failing-OP-TEE-tests.patch \
"

EXTRA_OEMAKE_append = " \
    LIBGCC_LOCATE_CFLAGS=--sysroot=${STAGING_DIR_HOST} \
"
