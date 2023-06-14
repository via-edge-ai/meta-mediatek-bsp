FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://MM21-0003-Port-plugins-to-gst_video_format_info_extrapolate_stride.patch \
	file://MM21-0004-video4linux2-Add-MM21-support.patch \
	file://MM21-0010-doc-Add-NV12_16L32S-into-the-cache.patch \
	file://0001-v4l2videoenc-set-caps-with-prepend-header-flag.patch \
	file://0002-v4l2object-add-mjpeg-decode-ym16-support.patch \
	file://0003-v4l2-bypass-check-some-transfer-types-in-colorimetry.patch \
	file://0004-v4l2object-Adjust-ENCODED_BUFFER_SIZE-for-4K-video.patch \
	file://0005-Support-dynamic-resolution-change.patch \
	file://0006-v4l2object-add-colorimetry-extensions-for-better-com.patch \
"
