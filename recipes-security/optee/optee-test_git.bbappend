FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
COMPATIBLE_MACHINE = "mt*"

PV = "3.11.0+git${SRCPV}"
SRCREV = "159e295d5cc3ad2275ab15fe544620f6604d4ba4"

SRC_URI_append = "\
	file://0001-HACK-fix-failing-OP-TEE-tests.patch \
"

EXTRA_OEMAKE_append = " \
    LIBGCC_LOCATE_CFLAGS=--sysroot=${STAGING_DIR_HOST} \
"
