SUMMARY = "MediaTek Bluetooth Test Tool"
LICENSE = "CLOSED"

SRC_URI = "${AIOT_NDA_URI}/neptune/bt_sw.git;protocol=ssh;branch=main;"
SRCREV = "9ed98c42cfb9a35ed34a966da67bafb967e2bdad"

#SRCREV_FORMAT = "default"


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

    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		install -d ${D}${systemd_system_unitdir}
		install -m 0644 ${WORKONSRC_bluetooth_tool}/mtk_btservice.service ${D}${systemd_system_unitdir}
	fi
}

do_uninstall:append() {
    /bin/bash ${WORKONSRC_bluetooth_tool}/script/yocto_clean_bluetooth.sh ${WORKONSRC_bluetooth_tool} ${COMBO_CHIP_ID}
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
