DESCRIPTION = "Lontium LT6911UXE out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "16e88d60abc9f65aed88d58cc3066e65dd45a46c"

BRANCH = "master"

SRC_URI += "${AIOT_BSP_URI}/lontium-lt6911-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-lontium-lt6911-driver"
