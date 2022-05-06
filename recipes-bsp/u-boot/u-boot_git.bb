require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

SRC_URI += " \
	file://0001-configs-mediatek-define-default-variables.patch \
	file://0002-configs-mediatek-enable-boot-via-extlinux.patch \
	file://0001-Revert-cmd-pxe_utils-Check-fdtcontroladdr-in-label_b.patch \
	file://fw_env.config \
"
