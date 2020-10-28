FILES_${PN}-mt8183-scp = " \
	${nonarch_base_libdir}/firmware/mediatek/mt8183/scp.img \
	${nonarch_base_libdir}/firmware/scp.img \
"
PACKAGES =+ "${PN}-mt8183-scp"

do_install_append_mt8183() {
	ln -s ${nonarch_base_libdir}/firmware/mediatek/mt8183/scp.img \
		${D}/${nonarch_base_libdir}/firmware/scp.img
}
