FILES_${PN}-mt8183-scp = " \
	${nonarch_base_libdir}/firmware/mediatek/mt8183/scp.img \
	${nonarch_base_libdir}/firmware/scp.img \
"
FILES_${PN}-mt7668 = "${nonarch_base_libdir}/firmware/mediatek/mt7668pr2h.bin"

PACKAGES =+ "${PN}-mt8183-scp"
PACKAGES =+ "${PN}-mt7668"

do_install_append_mt8183() {
	ln -s ${nonarch_base_libdir}/firmware/mediatek/mt8183/scp.img \
		${D}/${nonarch_base_libdir}/firmware/scp.img
}
