# Copyright (C) 2018 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek MT7668 bluetooth driver"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e1696b147d49d491bcb4da1a57173fff"

inherit module

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/mt7668-bt-mod.git;protocol=ssh;branch=v4.19"
SRCREV = "a84b779df5af5394dd07ba4593f2dcf1cf126e24"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = " \
	PLATFORM=MT8516_YOCTO \
	KERNEL_SRC=${STAGING_KERNEL_DIR} \
"

RDEPENDS_${PN} += "mt7668-bt-fw"
RPROVIDES_${PN} += "kernel-module-mt7668-bt"
