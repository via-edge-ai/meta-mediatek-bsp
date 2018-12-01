DESCRIPTION = "Pumpkin image with some development tools"

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
	${@bb.utils.contains("MACHINE_FEATURES", "alsa", "alsa-utils alsa-state", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "bluez5", "bluez5", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "bluetooth", "mt7668-bt-fw kernel-module-mtk-bt", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "zeroconf", "avahi-autoipd avahi-daemon avahi-utils avahi-dnsconfd", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "wifi", "mt7668-wifi-fw wpa-supplicant kernel-module-mtk-wlan", "", d)} \
	busybox \
	dropbear \
"

PACKAGE_EXCLUDE = "openssh"

#	pulseaudio
#	pulseaudio-server
#	pulseaudio-module-alsa-card
#	pulseaudio-misc
#	pulseaudio-module-bluetooth-policy
#	pulseaudio-module-bluez5-device
#	pulseaudio-module-bluez5-discover

EXTRA_IMAGEDEPENDS = " \
	lk \
	mbr \
	mtk-flash-tools \
	trustzone \
"

inherit core-image
