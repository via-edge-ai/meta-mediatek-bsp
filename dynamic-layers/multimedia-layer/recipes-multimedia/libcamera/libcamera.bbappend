SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/libcamera.git;protocol=ssh;branch=mtk/camsv"
SRCREV = "85cd4af7b112a7a2dd57c26d5e9f462fee509701"

PV = "202104+git${SRCPV}"

PACKAGECONFIG_append = " gst"
