MACHINEOVERRIDES =. "mt7663:"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS_append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "kernel-module-mt7663s", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "linux-firmware-mt7663", "", d)} \
"