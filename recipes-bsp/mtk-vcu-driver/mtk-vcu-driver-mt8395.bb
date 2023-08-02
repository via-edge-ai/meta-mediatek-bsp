# Copyright (C) 2023 Macross Chen <macross.chen@mediatek.com>

require mtk-vcu-driver-common.inc

# MT8195/MT8188: co-branch with mt8395
SRCREV = "821b44df1e29768d4008de827b413bae8d69bab8"
BRANCH = "mt8395"
TAR_PLATFORM = "mt8395"
REF_SYMBOLS_PATH = "${B}/mtk-vcodec-driver-mt8395/Module.symvers"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-mtk-vcu-driver-mt8395"
