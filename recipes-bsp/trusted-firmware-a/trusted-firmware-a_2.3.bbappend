require trusted-firmware-a-mtk.inc

SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/arm-trusted-firmware.git;name=tfa;branch=mtk-v2.3;protocol=ssh"
SRCREV_tfa = "698e8df1a5975675d118792d5529eb9311206385"

TFA_BUILD_TARGET = "bl31 fip"

inherit features_check
# optee is required because the bl2 binary requires it, and if we don't
# provide it, we won't be able to boot
REQUIRED_DISTRO_FEATURES = "optee"

do_deploy_append() {
	install -m 0644 ${S}/prebuilt/${TFA_PLATFORM}/release/bl2.img ${DEPLOYDIR}/
}
addtask do_deploy after do_install
