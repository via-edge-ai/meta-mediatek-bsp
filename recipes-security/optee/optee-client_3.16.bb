# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-client.inc

SRCREV = "06db73b3f3fdb8d23eceaedbc46c49c0b45fd1e2"
COMPATIBLE_MACHINE = "mt*"

EXTRA_OECMAKE:append = " -DCFG_TEE_PLUGIN_LOAD_PATH=${libdir}/tee-supplicant/plugins/"

PACKAGECONFIG ??= " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee-rpmb', 'rpmb', '', d)} \
"

PACKAGECONFIG[rpmb] = "-DRPMB_EMU=0, -DRPMB_EMU=1"
