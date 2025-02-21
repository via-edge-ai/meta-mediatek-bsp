FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "\
	file://0100-Modiy-GstBuffer-v4l2-buffer-timestamp-from-add-priva.patch \
	file://0001-Add-AiInference-information-in-GstBuffer.patch \
	file://0001-Bypass-AI-information-and-v4l2-timestamp-to-all-tran.patch \
"



