# First include a base image to base things off
#require recipes-core/images/core-image-minimal.bb
require recipes-extended/images/core-image-full-cmdline.bb

CORE_IMAGE_EXTRA_INSTALL += "wlan"

IMAGE_INSTALL_append = " \
	busybox \
	wpa-supplicant \
"
