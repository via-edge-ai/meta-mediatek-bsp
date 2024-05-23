FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-Do-not-show-payload-info-if-show_payload-flag-off.patch \
	file://0002-v4l-utils-cec-ctl-resend-standby-in-init_power_cycle.patch \
"
