# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Audio packages"

inherit packagegroup

ALSA_PKGS = " \
	alsa-utils \
	alsa-state \
"

PULSEAUDIO_BLUEZ5_PKGS = " \
	pulseaudio-module-bluez5-device \
	pulseaudio-module-bluez5-discover \
"

PULSEAUDIO_BT_PKGS = " \
	pulseaudio-module-bluetooth-policy \
	${@bb.utils.contains("DISTRO_FEATURES", "bluez5", "${PULSEAUDIO_BLUEZ5_PKGS}", "", d)} \
"

PULSEAUDIO_PKGS = " \
	pulseaudio \
	pulseaudio-server \
	pulseaudio-module-alsa-card \
	pulseaudio-misc \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "${PULSEAUDIO_BT_PKGS}", "", d)} \
"

RDEPENDS_${PN} = " \
	${@bb.utils.contains("DISTRO_FEATURES", "alsa", "${ALSA_PKGS}", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "pulseaudio", "${PULSEAUDIO_PKGS}", "", d)} \
"
