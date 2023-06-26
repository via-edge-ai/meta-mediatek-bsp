require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

SRC_URI += " \
	file://0001-Revert-cmd-pxe_utils-Check-fdtcontroladdr-in-label_b.patch \
	file://0002-mediatek-bootlogo-remove-video-device-before-enter-kernel.patch \
	file://fw_env.config \
"
