# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Zeroconf packages for pumpkin board"

inherit packagegroup

ZEROCONF_PKGS = " \
	avahi-autoipd \
	avahi-daemon \
	avahi-utils \
	avahi-dnsconfd \
"
RDEPENDS_${PN} = " \
	${@bb.utils.contains("DISTRO_FEATURES", "zeroconf", "${ZEROCONF_PKGS}", "", d)} \
"
