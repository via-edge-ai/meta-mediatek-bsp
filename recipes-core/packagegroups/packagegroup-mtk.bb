# Copyright (C) 2019 Fabien Parent <fparent@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "MediaTek userspace packages"

inherit packagegroup

RDEPENDS_${PN}_mt8167 = " \
	mtk-mdpd \
	mtk-vpud \
"
