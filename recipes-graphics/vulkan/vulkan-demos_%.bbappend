FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://0001-Use-correct-filepath-to-find-TTF-resource.patch \
"
