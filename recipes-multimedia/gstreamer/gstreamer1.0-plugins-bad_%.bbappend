FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://MM21-0003-Port-plugins-to-gst_video_format_info_extrapolate_stride.patch \
	file://MM21-0007-v4l2codecs-Add-support-for-multi-planar-buffers.patch \
	file://MM21-0008-v4l2codecs-Enable-MediaTek-MM21-NV12_16L32S.patch \
	file://MM21-0010-doc-Add-NV12_16L32S-into-the-cache.patch \
	file://kmssink-0001-Add-mediatek-into-default-driver-list.patch \
"

PACKAGECONFIG:append = " kms"

