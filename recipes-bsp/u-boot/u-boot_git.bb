require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

UBOOT_MAKE_TARGET_append = " u-boot-initial-env "

do_deploy() {
	install -m 0644 ${B}/u-boot-initial-env ${DEPLOYDIR}
}

do_deploy_append_pumpkin() {
	boot_conf=`echo "boot_conf=#conf@${KERNEL_DEVICETREE}" | tr '/' '_'`

	if [ ${@bb.utils.contains("MACHINE_FEATURES", "vesper-hat", "y", "n", d)} = "y" ] &&
	   [ ${@bb.utils.contains("KERNEL_DEVICETREE_OVERLAYS", "vesper.dtbo", "y", "n", d)} = "y" ]
	then
		boot_conf="$boot_conf#conf@vesper.dtbo"
	fi

	if [ ${@bb.utils.contains("MACHINE_FEATURES", "screen", "y", "n", d)} = "y" ] &&
	   [ ${@bb.utils.contains("KERNEL_DEVICETREE_OVERLAYS", "rpi-display.dtbo", "y", "n", d)} = "y" ]
	then
		boot_conf="$boot_conf#conf@rpi-display.dtbo"
	fi

	echo $boot_conf >> ${DEPLOYDIR}/u-boot-initial-env
}

inherit deploy

SYSROOT_DIRS += " /boot"
