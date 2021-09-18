MACHINEOVERRIDES =. "mt7668:"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS:append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk wifi", "kernel-module-mt7668-tk-wifi", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk bluetooth", "linux-firmware-mt7668", "", d)} \
"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "wifitest", "", d)} \
"
