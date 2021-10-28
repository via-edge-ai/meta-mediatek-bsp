COMPATIBLE_MACHINE = "mt*"

EXTRA_OECMAKE:append = " -DCFG_TEE_PLUGIN_LOAD_PATH=${libdir}/tee-supplicant/plugins/"
