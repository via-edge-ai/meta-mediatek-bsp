MTK_WIRELESS_RELEASE ?= "alps-release-r0.mp5-aiot"
MTK_WIRELESS_CHIP = "MT7663"
MACHINEOVERRIDES =. "mt7663:"

MACHINE_EXTRA_RRECOMMENDS:append = " \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk wifi", "kernel-module-mt7663-tk-wifi", "kernel-module-mt7663s", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "linux-firmware-mt7663", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk wifi", "", "mt7663-service", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "nda-mtk", "wifitest", "", d)} \
"
