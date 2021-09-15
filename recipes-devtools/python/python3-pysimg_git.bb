# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Pure-Python tools for handling Android fastboot's sparse image format"
HOMEPAGE = "https://github.com/dlenski/PySIMG"
SECTION = "devel/python"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://README.md;beginline=62;md5=5f24b33dfbaa5351a11e5491f73d7930"

SRC_URI = "git://github.com/dlenski/PySIMG.git"
SRCREV = "f6eae13209d44396161b5849680f247fdeb8e1f5"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native"
