require u-boot-common_${PV}.inc
require u-boot.inc

DEPENDS += "bc-native dtc-native"

UBOOT_MAKE_TARGET_append = " u-boot-initial-env "

do_deploy() {
	install -m 0644 ${B}/u-boot-initial-env ${DEPLOYDIR}
}

do_deploy_append_pumpkin() {
	if [ ${@bb.utils.contains("MACHINE_FEATURES", "vesper-hat", "y", "n", d)} = "y" ];
	then
		echo "boot_conf=#conf@${KERNEL_DEVICETREE}#conf@vesper.dtbo" | \
			tr '/' '_' >> ${DEPLOYDIR}/u-boot-initial-env;
	fi
}

inherit deploy

SYSROOT_DIRS += " /boot"
