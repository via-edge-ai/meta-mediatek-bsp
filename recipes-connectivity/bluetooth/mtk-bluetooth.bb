SUMMARY = "MediaTek Bluetooth Test Tool"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_others/vendor_lib.git;protocol=ssh;name=vendorlib;branch=main;destsuffix=git/bt_others/vendor_lib "
SRC_URI_append = "${AIOT_NDA_URI}/neptune/bt_others/picus.git;protocol=ssh;name=picus;branch=main;destsuffix=git/bt_others/picus "
SRC_URI_append = "${AIOT_NDA_URI}/neptune/bt_others/bluetooth_mw.git;protocol=ssh;name=btmw;branch=main;destsuffix=git/bt_others/bluetooth_mw "
SRC_URI_append = "${AIOT_NDA_URI}/neptune/bt_others/bluetooth_tool.git;protocol=ssh;name=bttool;branch=main;destsuffix=git/bt_others/bluetooth_tool "
SRC_URI_append = "${AIOT_NDA_URI}/neptune/bt_stack/bluedroid_turnkey.git;protocol=ssh;name=btstack;branch=main;destsuffix=git/bt_stack/bluedroid_turnkey "
SRC_URI_append = "${AIOT_NDA_URI}/neptune/bt_others/boots.git;protocol=ssh;name=boots;branch=main;destsuffix=git/bt_others/boots "

SRCREV_vendorlib = "aff8e92004335da8a9d887243fa39d4da88bb2f7"
SRCREV_picus = "c693c711ea21593fd0e6b7d8d1ab24d61ded2202"
SRCREV_btmw = "0d51e468ae8fa216ff18866e734ea44e53198963"
SRCREV_bttool = "d11eaa2fdcc8012cd54afed70f853b086c96e40f"
SRCREV_btstack = "26dbb327da465c2d53a37314602f8b7d1ba1250e"
SRCREV_boots = "7886d78ab18f5f2c7e0611a014ffe0e2b5db5570"
SRCREV_FORMAT = "vendorlib_picus_btmw_bttool_btstack_boots_picus"

#PV = "git${SRCPV}"

S = "${WORKDIR}/git"

WORKONSRC_bluetooth_tool = "${S}/bt_others/bluetooth_tool"
COMBO_CHIP_ID = "mt7663"

inherit systemd
SYSTEMD_PACKAGES = "${PN}"

DEPENDS += "libevent openssl zlib libxml2 alsa-lib"

do_compile() {
        echo bluetooth start compile
        export ENABLE_SYS_LOG="no"
        #echo bluetooth start compile and sys log:$ENABLE_SYS_LOG

        if ${@bb.utils.contains('LICENSE_FLAGS_AAC', 'yes', 'true', 'false' ,d)}; then
            export SUPPORT_AAC="yes"
        else
            export SUPPORT_AAC="no"
        fi
        export SUPPORT_IPDC="no"
        export SUPPORT_SPP="no"
        export SUPPORT_HIDH="no"
        export SUPPORT_HIDD="no"
        export SUPPORT_GATT="yes"
        export SUPPORT_AVRCP="no"
        export SUPPORT_A2DP_SRC="no"
        export SUPPORT_A2DP_ADEV="no"
        export SUPPORT_A2DP_SINK="no"
        export SUPPORT_A2DP_48000="no"


        FOR_BT_VENDOR="no"
        cd ${WORKONSRC_bluetooth_tool}/script
        echo MTK_BT_C4A = ${MTK_BT_C4A}
        /bin/bash generate_environment.sh ${COMBO_CHIP_ID} ${FOR_BT_VENDOR}
        if [ $? -ne 0 ]; then
            echo bluetooth generate environment fail!!
            exit 1
        fi
        cd ${WORKONSRC_bluetooth_tool}
        echo support aac codec ? ${SUPPORT_AAC}
        /bin/bash ${WORKONSRC_bluetooth_tool}/script/yocto_build_bluetooth.sh ${WORKONSRC_bluetooth_tool} ${COMBO_CHIP_ID} ${STAGING_LIBDIR}
        if [ $? -ne 0 ]; then
            echo bluetooth compile fail!!
            exit 1
        fi
        echo bluetooth end compile
}

do_install:append() {
    install -d ${D}${libdir}
    install -m 755 ${WORKONSRC_bluetooth_tool}/prebuilts/lib/* ${D}${libdir}/

    install -d ${D}/usr/bin
    install -m 0755 ${WORKONSRC_bluetooth_tool}/prebuilts/bin/* ${D}/usr/bin/

    install -d ${D}/data/misc/bluedroid
    install -m 0640 ${WORKONSRC_bluetooth_tool}/prebuilts/conf/* ${D}/data/misc/bluedroid/

    install -d ${D}${libdir}
    install -m 755 ${WORKONSRC_bluetooth_tool}/vendor_prebuilts/lib/* ${D}${libdir}/

    install -d ${D}/usr/bin
    install -m 0755 ${WORKONSRC_bluetooth_tool}/vendor_prebuilts/bin/* ${D}/usr/bin/

    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		install -d ${D}${systemd_system_unitdir}
		install -m 0644 ${WORKONSRC_bluetooth_tool}/mtk_btservice.service ${D}${systemd_system_unitdir}
	fi
}

do_uninstall:append() {
    /bin/bash ${WORKONSRC_bluetooth_tool}/script/yocto_clean_bluetooth.sh ${WORKONSRC_bluetooth_tool} ${COMBO_CHIP_ID}
	/bin/bash ${WORKONSRC_bluetooth_tool}/script/yocto_clean_vendor.sh ${WORKONSRC_bluetooth_tool} ${COMBO_CHIP_ID}
}


FILES_${PN}-dev = ""
INSANE_SKIP_${PN} += "ldflags"


SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'mtk_btservice.service', '', d)}"

FILES:${PN} += " \
	${systemd_system_unitdir}/mtk_btservice.service \
	/data/misc/bluedroid ${libdir} \
"


RPROVIDES:${PN} += " \
	mtk-bluetooth \
"
