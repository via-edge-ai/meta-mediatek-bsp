inherit deploy

COMPATIBLE_MACHINE = "mt*"

TFA_UBOOT = "1"

DEPENDS_append = " coreutils-native u-boot-tools-native"

EXTRA_OEMAKE += "CFLAGS=-DBOARD_${TFA_BOARD_NAME}"
EXTRA_OEMAKE += "E=0"
EXTRA_OEMAKE += "NEED_BL33=yes"

# OP-TEE
DEPENDS_append = " ${@bb.utils.contains("DISTRO_FEATURES", "optee", "optee-os", "", d)}"
TFA_SPD = "${@bb.utils.contains("DISTRO_FEATURES", "optee", "opteed", "", d)}"
EXTRA_OEMAKE_append = " \
	BL32=${STAGING_DIR_TARGET}/${nonarch_base_libdir}/firmware/tee.bin \
	NEED_BL32=${@bb.utils.contains("DISTRO_FEATURES", "optee", "yes", "no", d)} \
	CFLAGS+=${@bb.utils.contains("DISTRO_FEATURES", "optee", "-DNEED_BL32", "", d)} \
"
