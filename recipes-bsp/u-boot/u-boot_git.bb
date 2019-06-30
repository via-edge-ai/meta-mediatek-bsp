require u-boot-common_${PV}.inc
require u-boot.inc

DEPENDS += "bc-native dtc-native"

UBOOT_MAKE_TARGET_append = " u-boot-initial-env "

do_deploy() {
	install -m 0644 ${B}/u-boot-initial-env ${DEPLOYDIR}
}

inherit deploy

SYSROOT_DIRS += " /boot"
