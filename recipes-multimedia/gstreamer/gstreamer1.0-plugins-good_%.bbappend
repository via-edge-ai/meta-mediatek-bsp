FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-sys-v4l2-add-support-for-MT21-format.patch \
"

SRC_URI_append_i300a = " \
	file://0002-HACK-don-t-resize-buffers.patch \
	file://0003-HACK-don-t-update-width-height.patch \
"

SRC_URI_append_mt8183 = " \
	file://0002-HACK-don-t-resize-buffers.patch \
	file://0003-HACK-don-t-update-width-height.patch \
"
