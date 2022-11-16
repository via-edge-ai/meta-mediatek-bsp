DESCRIPTION = "Mali GPU kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"


inherit module

SRCREV = "9a60908c3832c23320ec109fb1f2d76477d76a0d"

BRANCH = "master"

SRC_URI += "${AIOT_BSP_URI}/mtk-mali-gpu-driver.git;protocol=https;branch=${BRANCH} \
"


S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-gpu"

