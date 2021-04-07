SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/libcamera.git;protocol=ssh;branch=mtk/camsv"
SRCREV = "b57162f0079ead0c5e81920f36afe0a269869a2e"

PV = "202011+git${SRCPV}"

DEPENDS_append = " python3-jinja2-native python3-ply-native"

PACKAGECONFIG_append = " gst"
