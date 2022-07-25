FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://MM21-0001-video-Add-NV12_16L32S-aka-Mediatek-MM21-support.patch \
	file://MM21-0002-Add-an-helper-to-extrapolate-strides.patch \
	file://MM21-0009-tests-video-Add-a-unit-test-for-stride-extrapolation.patch \
	file://MM21-0010-doc-Add-NV12_16L32S-into-the-cache.patch \
	file://MM21-0011-video-Fix-NV12_16L32-size-calculation.patch \
	file://MM21-0012-gstrawvideoparse-Fix-frame-size-calculation.patch \
"
