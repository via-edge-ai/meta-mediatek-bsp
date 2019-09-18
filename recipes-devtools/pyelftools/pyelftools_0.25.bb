SUMMARY = "Python library for analyzing ELF files and DWARF debugging information"

HOMEPAGE = "http://funcsigs.readthedocs.org"
SECTION = "devel/python"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5ce2a2b07fca326bc7c146d10105ccfc"

SRC_URI[md5sum] = "c5629b9a5d19c82107a946cce52eeec2"
SRC_URI[sha256sum] = "89c6da6f56280c37a5ff33468591ba9a124e17d71fe42de971818cbff46c1b24"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
