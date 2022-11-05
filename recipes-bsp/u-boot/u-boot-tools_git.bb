require u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot-tools.inc

DEPENDS += "gnutls openssl util-linux swig-native python3-setuptools-native"

inherit python3native