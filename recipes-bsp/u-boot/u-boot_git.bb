require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native u-boot-tools-native"

SRC_URI += " \
	file://fw_env.config \
	file://boot.script \
"

do_deploy:append() {
	boot_conf=`echo "boot_conf=#conf-${KERNEL_DEVICETREE}" | tr '/' '_'`
	fastboot_entry=`echo "check_fastboot_entry=setenv fastboot_entry 0"`
	storage=`echo "storage=mmc"`
	storage_dev=`echo "storage_dev=0"`

	if [ "${@bb.utils.contains('MACHINE_FEATURES', 'ufs-boot', 'ufs-boot', '', d)}" = "ufs-boot" ]; then
		storage=`echo "storage=scsi"`
		storage_dev=`echo "storage_dev=2"`
	fi

	for dtbo in ${KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD};
	do
		boot_conf="$boot_conf#conf-$dtbo"
	done

	echo $fastboot_entry >> ${DEPLOYDIR}/u-boot-initial-env
	echo $boot_conf >> ${DEPLOYDIR}/u-boot-initial-env
	echo $storage >> ${DEPLOYDIR}/u-boot-initial-env
	echo $storage_dev >> ${DEPLOYDIR}/u-boot-initial-env
	echo "boot_scripts=fitImage" >> ${DEPLOYDIR}/u-boot-initial-env
}

do_deploy:append:i300-pumpkin() {
	sed -i '/^check_fastboot_entry=.*/c\check_fastboot_entry=gpio input 42; if test $? -eq 0; then setenv fastboot_entry 1; else setenv fastboot_entry 0; fi' ${DEPLOYDIR}/u-boot-initial-env
}

inherit deploy

SYSROOT_DIRS += " /boot"
