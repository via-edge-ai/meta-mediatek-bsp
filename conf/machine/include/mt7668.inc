MACHINEOVERRIDES =. "mt7668:"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS_append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk wifi", "kernel-module-mt7668-wifi", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk bluetooth", "linux-firmware-mt7668", "", d)} \
"

MACHINE_EXTRA_RRECOMMENDS_append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "wifitest", "", d)} \
"
