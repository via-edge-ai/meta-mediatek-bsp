FILES_${PN}-mt8183-scp = " \
	${nonarch_base_libdir}/firmware/mediatek/mt8183/scp.img \
	${nonarch_base_libdir}/firmware/scp.img \
"
FILES_${PN}-mt7668 = "${nonarch_base_libdir}/firmware/mediatek/mt7668pr2h.bin"
FILES_${PN}-mt7663 = " \
	${nonarch_base_libdir}/firmware/mediatek/mt7663_n9_rebb.bin \
	${nonarch_base_libdir}/firmware/mediatek/mt7663_n9_v3.bin \
	${nonarch_base_libdir}/firmware/mediatek/mt7663pr2h.bin \
	${nonarch_base_libdir}/firmware/mediatek/mt7663pr2h_rebb.bin \
"

PACKAGES =+ "${PN}-mt8183-scp"
PACKAGES =+ "${PN}-mt7668"
PACKAGES =+ "${PN}-mt7663"

do_install_append_mt8183() {
	ln -s ${nonarch_base_libdir}/firmware/mediatek/mt8183/scp.img \
		${D}/${nonarch_base_libdir}/firmware/scp.img
}
