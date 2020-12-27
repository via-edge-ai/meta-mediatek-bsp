require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native u-boot-tools-native"

SRC_URI += " \
	file://fw_env.config \
	file://boot.script \
"

do_deploy_append() {
	boot_conf=`echo "boot_conf=#conf@${KERNEL_DEVICETREE}" | tr '/' '_'`

	for dtbo in ${KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD};
	do
		boot_conf="$boot_conf#conf@$dtbo"
	done

	echo $boot_conf >> ${DEPLOYDIR}/u-boot-initial-env
}

inherit deploy

SYSROOT_DIRS += " /boot"

do_compile_append() {
	uboot-mkimage -A arm -T script -O linux -d ${WORKDIR}/boot.script \
		${WORKDIR}/boot.scr
}

PACKAGE_BEFORE_PN += "${PN}-scr"

FILES_${PN}-scr = " \
    /boot/boot*.scr \
"

RDEPENDS_${PN} += "${PN}-scr"
