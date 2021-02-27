MACHINEOVERRIDES =. "mt7668:"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS_append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "kernel-module-mt7668-wifi", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "linux-firmware-mt7668", "", d)} \
"

MACHINE_EXTRA_RRECOMMENDS_append = " \
	wifitest \
"
