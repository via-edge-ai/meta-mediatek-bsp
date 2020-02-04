require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

UBOOT_MAKE_TARGET_append = " u-boot-initial-env "

do_deploy() {
	install -m 0644 ${B}/u-boot-initial-env ${DEPLOYDIR}

	boot_conf=`echo "boot_conf=#conf@${KERNEL_DEVICETREE}" | tr '/' '_'`

	for dtbo in ${KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD};
	do
		boot_conf="$boot_conf#conf@$dtbo"
	done

	echo $boot_conf >> ${DEPLOYDIR}/u-boot-initial-env
}

inherit deploy

SYSROOT_DIRS += " /boot"
