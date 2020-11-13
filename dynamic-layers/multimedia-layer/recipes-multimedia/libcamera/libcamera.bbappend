SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/libcamera.git;protocol=ssh;branch=mtk/camsv"
SRCREV = "85787087c660ab1d84d0788bbc55c85fddddc328"

PV = "202011+git${SRCPV}"

DEPENDS_append = " python3-jinja2-native python3-ply-native"

PACKAGECONFIG_append = " gst"
