require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native u-boot-tools-native"

SRC_URI += " \
	file://fw_env.config \
	file://boot.script \
"

do_deploy_append() {
	boot_conf=`echo "boot_conf=#conf@${KERNEL_DEVICETREE}" | tr '/' '_'`
	fastboot_entry=`echo "check_fastboot_entry=setenv fastboot_entry 0"`

	for dtbo in ${KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD};
	do
		boot_conf="$boot_conf#conf@$dtbo"
	done

	echo $fastboot_entry >> ${DEPLOYDIR}/u-boot-initial-env
	echo $boot_conf >> ${DEPLOYDIR}/u-boot-initial-env
}

do_deploy_append_i300-pumpkin() {
	sed -i '/^check_fastboot_entry=.*/c\check_fastboot_entry=gpio input 42; if test $? -eq 0; then setenv fastboot_entry 1; else setenv fastboot_entry 0; fi' ${DEPLOYDIR}/u-boot-initial-env
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
