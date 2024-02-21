DESCRIPTION = "Mediatek CAMISP-MT8395 Out-of-tree kernel driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=16de935ebcebe2420535844d4f6faefc"

COMPATIBLE_MACHINE = "mt8395|mt8390|mt8370"

inherit module

SRCREV = "65bf32c3e516a28343681a5a15891e10f1294081"

BRANCH = "master"

SRC_URI += "${AIOT_BSP_URI}/mtk-camisp-driver.git;protocol=https;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-camisp-driver"

python() {
    plat = d.getVar('SOC_FAMILY', True)
    if plat == 'mt8370':
        plat = 'mt8188'
    d.setVar('PLATFORM', plat)
}

EXTRA_OEMAKE:append = " PLATFORM=${PLATFORM} "

do_install:append() {
    install -d ${D}${nonarch_base_libdir}/firmware/
    dd if=/dev/zero of=${D}${nonarch_base_libdir}/firmware/remoteproc_scp bs=1 count=100
}

FILES:${PN} += " \
    ${nonarch_base_libdir}/firmware/remoteproc_scp \
"
