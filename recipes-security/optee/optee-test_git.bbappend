FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "\
	file://0001-HACK-fix-failing-OP-TEE-tests.patch \
"

PV = "3.6.0+git${SRCPV}"
SRCREV = "40aacb6dc33bbf6ee329f40274bfe7bb438bbf53"

inherit python3native

SRC_URI_remove = "file://fix-build-failure-with-GCC6.patch"
SRC_URI_remove = "file://regression-4011-correct-potential-overflow.patch"
SRC_URI_remove = "file://xtest-prevent-unexpected-build-warning-with-strncpy.patch"
