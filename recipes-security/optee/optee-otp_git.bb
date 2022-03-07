SUMMARY = "OP-TEE OTP"
DESCRIPTION = "An application to emulate an OTP storage"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e06794dca4078e0008c8cd23edeea1dd"

DEPENDS = "optee-client optee-os-tadevkit python3-pycryptodome-native"
DEPENDS += " libgcc"

REQUIRED_DISTRO_FEATURES = "optee-otp"

inherit deploy python3native features_check

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

SRC_URI = "${AIOT_BSP_URI}/optee-otp.git;branch=main;;protocol=ssh"
SRCREV = "2a7fd60f09825546c4342b0b1783d174388274d1"

COMPATIBLE_MACHINE = "mt*"

OPTEE_CLIENT_EXPORT = "${STAGING_DIR_HOST}${prefix}"
TEEC_EXPORT = "${STAGING_DIR_HOST}${prefix}"
TA_DEV_KIT_DIR = "${STAGING_INCDIR}/optee/export-user_ta"

EXTRA_OEMAKE += '\
    CFG_FTPM_USE_WOLF=y \
    TA_DEV_KIT_DIR=${TA_DEV_KIT_DIR} \
    TA_CROSS_COMPILE=${TARGET_PREFIX} \
    CROSS_COMPILE=${TARGET_PREFIX} \
    CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_HOST} -I${WORKDIR}/optee-os -I${S}/ta/include" \
'

EXTRA_OEMAKE:append:aarch64 = "\
    CFG_ARM64_ta_arm64=y \
"

do_compile() {
    oe_runmake -C ${S}/ta
    oe_runmake -C ${S}/host
}

do_install () {
    mkdir -p ${D}${bindir}
    install -D -p -m0755 ${S}/host/optee_otp ${D}${bindir}
}

do_deploy () {
    install -d ${DEPLOYDIR}/optee
    install -D -p -m0444 ${S}/ta/*.stripped.elf ${DEPLOYDIR}/optee/
}

addtask deploy before do_build after do_install

INSANE_SKIP:${PN} += "ldflags"

# This is specific to the machine
PACKAGE_ARCH = "${MACHINE_ARCH}"
