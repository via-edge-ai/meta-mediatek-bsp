# Copyright (C) 2021 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-security/optee/optee-client.inc

SRCREV = "182874320395787a389e5b0f7df02b32f3c0a1b0"
COMPATIBLE_MACHINE = "mt*"

EXTRA_OECMAKE:append = " -DCFG_TEE_PLUGIN_LOAD_PATH=${libdir}/tee-supplicant/plugins/"
