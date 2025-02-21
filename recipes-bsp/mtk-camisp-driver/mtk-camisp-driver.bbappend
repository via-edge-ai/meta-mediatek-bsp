
SRC_URI:append = "\
	file://0001-Add-the-driver-for-imx258-camera.patch \
	file://0002-Add-the-driver-for-ov5648-camera.patch \
	file://0003-Reduce-the-logs-from-the-drivers-for-imx258-ov5648-c.patch \
"

do_patch() {
	git -C ${S} am --keep-cr ${WORKDIR}/0001-Add-the-driver-for-imx258-camera.patch
	git -C ${S} am --keep-cr ${WORKDIR}/0002-Add-the-driver-for-ov5648-camera.patch
	git -C ${S} am --keep-cr ${WORKDIR}/0003-Reduce-the-logs-from-the-drivers-for-imx258-ov5648-c.patch
}

