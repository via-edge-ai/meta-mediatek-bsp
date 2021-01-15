SRC_URI = "git://git@gitlab.com/baylibre/rich-iot/device/libcamera.git;protocol=ssh;branch=mtk/camsv"
SRCREV = "879d8dab2e19faca6cfe5df21fdaaebc5a38be36"

PV = "202011+git${SRCPV}"

DEPENDS_append = " python3-jinja2-native python3-ply-native"

PACKAGECONFIG_append = " gst"
