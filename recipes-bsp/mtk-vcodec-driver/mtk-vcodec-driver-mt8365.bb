# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcodec-driver-common.inc

# MT8365: co-branch with mt8167
SRCREV = "7f9b9fe7cedad4018bc9e602e72705811ea01ecf"
BRANCH = "mt8167"
TAR_PLATFORM = "mt8365"
REF_SYMBOLS_PATH = "${B}/mtk-vcu-driver-mt8365/Module.symvers"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-vcodec-driver-mt8365"
