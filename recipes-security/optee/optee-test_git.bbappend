FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += "python3-pycryptodomex-native"

PV = "3.8.0+git${SRCPV}"
SRCREV = "30481e381cb4285706e7516853495a7699c93b2c"

SRC_URI_append = "\
	file://0001-HACK-fix-failing-OP-TEE-tests.patch \
"
