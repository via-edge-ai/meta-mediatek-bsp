FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "\
	file://0001-HACK-fix-failing-OP-TEE-tests.patch \
"
