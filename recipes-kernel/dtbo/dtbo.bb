# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Device-Tree Blob Overlays"

inherit devicetree

DT_FILES_PATH = "${WORKDIR}"

COMPATIBLE_MACHINE = "mt*"
