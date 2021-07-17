SRC_URI = "${AIOT_BSP_URI}/libcamera.git;protocol=ssh;branch=mtk-aiot"
SRCREV = "7799466db6b7ea447afe987a30fd7883c09c3d9b"

PV = "202104+git${SRCPV}"

PACKAGECONFIG_append = " gst"
