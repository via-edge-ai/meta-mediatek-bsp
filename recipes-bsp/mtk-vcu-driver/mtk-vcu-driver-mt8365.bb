# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8365: co-branch with mt8167
SRCREV = "aee03138d90e7706beaac989e36ec0cc11514dbd"
BRANCH = "mt8167"
TAR_PLATFORM = "mt8365"
REF_SYMBOLS_PATH = ""


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-vcu-driver-mt8365"
