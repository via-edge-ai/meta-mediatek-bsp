require recipes-bsp/mtk-secure-boot-tools/mtk-secure-boot-tools.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/test-keys:"

SRC_URI:append = " \
    file://efuse.pem \
    file://da.pem \
"

COMPATIBLE_MACHINE = "i350-sb35|i350-evk|i500-pumpkin"

DEFAULT_EFUSE_KEY = "${WORKDIR}/efuse.pem"
DEFAULT_DA_KEY = "${WORKDIR}/da.pem"
EFUSE_KEY ?= "${DEFAULT_EFUSE_KEY}"
DA_KEY ?= "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '${DEFAULT_DA_KEY}', '', d)}"

EXTRA_OEMAKE = "OUTDIR=${WORKDIR} EFUSE_KEY=${EFUSE_KEY} DA_KEY=${DA_KEY}"
EXTRA_OEMAKE:append = " EFUSE_KEY1=${EFUSE_KEY1} EFUSE_KEY2=${EFUSE_KEY2} EFUSE_KEY3=${EFUSE_KEY3}"
EXTRA_OEMAKE:append:i500-pumpkin = " PLATFORM=i500_pumpkin"
EXTRA_OEMAKE:append:i350-sb35 = " PLATFORM=i350_sb35"
EXTRA_OEMAKE:append:i350-evk = " PLATFORM=i350_sb35"

DEPENDS:append = " openssl-native util-linux-native"

do_compile() {
    if [ "${@oe.utils.conditional('DA_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
        if [ "x${DEFAULT_DA_KEY}" = "x${DA_KEY}" ]; then
            bbwarn "DA_KEY is not defined in local.conf, using development key for secure boot"
        fi
    fi

    if [ "${@oe.utils.conditional('BL2_SIGN_ENABLE', '1', '1', '', d)}" = "1" ]; then
        if [ "x${DEFAULT_EFUSE_KEY}" = "x${EFUSE_KEY}" ]; then
            bbwarn "BL2_KEY is not defined in local.conf, using development key for secure boot"
        fi
    fi

    SOC=`echo ${SOC_FAMILY} | tr [:lower:] [:upper:]`
    make ${EXTRA_OEMAKE} SOC=${SOC} -C ${S}/configs
}
