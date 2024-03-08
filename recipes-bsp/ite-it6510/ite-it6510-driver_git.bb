DESCRIPTION = "ITE IT6510 out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

inherit module

SRCREV = "368a511c3b434423e2e6af8fb9343f6eb4fe381f"

BRANCH = "master"

SRC_URI += "${AIOT_BSP_URI}/ite-it6510-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-ite-it6510-driver"
