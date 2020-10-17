COMPATIBLE_MACHINE = "mt*"

PV = "3.11.0+git${SRCPV}"
SRCREV = "9a7dc598591990349d88b4dba3a37aadd6851295"

EXTRA_OEMAKE_append = " \
    LIBGCC_LOCATE_CFLAGS=--sysroot=${STAGING_DIR_HOST} \
"

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"
