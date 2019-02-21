# Copyright (C) 2018 Phong LE <ple@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Multimedia packages"

inherit packagegroup

GST_PKGS = " \
	gstreamer1.0 \
	gstreamer1.0-plugins-base \
	gstreamer1.0-plugins-good \
	gstreamer1.0-plugins-bad \
	${@bb.utils.contains('LICENSE_FLAGS_WHITELIST', 'commercial', 'gstreamer1.0-plugins-ugly', '', d)} \
	${@bb.utils.contains('LICENSE_FLAGS_WHITELIST', 'commercial', 'gstreamer1.0-libav', '', d)} \
"

MISC_PKGS = " \
	libopus \
	portaudio-v19 \
"

RDEPENDS_${PN} = " \
	${MISC_PKGS} \
	${GST_PKGS} \
"
