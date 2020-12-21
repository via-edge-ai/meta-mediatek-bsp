i300 Pumpkin Board
==================

The i300 pumpkin board comes in two variants:

* `i300B / MT8362A SoC`_, 1GB DRAM, 4GB eMMC
* `i300A / MT8362A SoC`_, 2GB DRAM, 8GB eMMC

The i300 pumpkin board is using the :ref:`platforms/i300:i300 (MT8362)` platform.

Purchasing
----------

The i300 pumpkin boards are available on `Seeed`_:

* `Pumpkin EVK - Smart Audio Edition`_ (i300B / MT8362)
* `Pumpkin EVK - Smart Hub Edition`_ (i300A / MT8362)

Building
--------

You can build an image for the i300 pumpkin board by setting the
`MACHINE` variable in your `local.conf` to `i300a-pumpkin` or `i300b-pumpkin`.

.. code::

	MACHINE=i300a-pumpkin
	MACHINE=i300b-pumpkin

For full build instructions, please refer to the top level `README.md`.

Display
-------

The i300A Pumpkin support HDMI and the `7-inch Raspberry Pi touch display`_.

HDMI is working by default if you set `MACHINE=i300a-pumpkin`. If you wish to use the 7-inch Raspberry Pi touch display, you need to add the following to your `local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		panel-raspberrypi.dtbo \
	"

Warning: Adding `panel-raspberrypi.dtbo` to KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD
requires you to plug the 7-inch Raspberry Pi touch display to
the i300A pumpkin board. Failing to connect it will prevent HDMI from working.
If you only want HDMI, you should not add `panel-raspberrypi.dtbo`.

The i300A Pumpkin also supports the AVD-TT70WS-CN-134-A touch display. If you wish to use it, you need to add the following to your `local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		panel-avd-tt70ws-cn-134-a.dtbo \
	"

Cameras
-------

The pumpkin board supports the following csi camera sensor:

* Omnivision ov5645

If you wish to use the ov5645 camera, you need to add the following to your
`local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		camera-ov5645.dtbo \
	"

Audio
-----

By default, the pumpkin board ouputs audio on the jack connector.

It is possible to output the audio on the MT6392 PMIC mono lineout as well. To enable this output, you will need to change one of the alsa settings using the following command:

.. prompt:: bash $

	amixer set -c mtsndcard 'Codec_Loopback_Select',0 CODEC_LOOPBACK_DMIC_TO_SPK

Setting any loopback to the speaker will work.

In order to disable this audio output, simply disable the loopback by using the following command:

.. prompt:: bash $

	amixer set -c mtsndcard 'Codec_Loopback_Select',0 CODEC_LOOPBACK_NONE

Device-Tree Blob Overlays (DTBO)
--------------------------------

The following Device-Tree Overlays are supported:

* `panel-raspberrypi.dtbo`: Raspberry Pi touch display (see :ref:`boards/i300-pumpkin:Display` section)
* `panel-avd-tt70ws-cn-134-a.dtbo`: AVD-TT70WS-CN-134-A touch display module (see :ref:`boards/i300-pumpkin:Display` section)
* `camera-ov5645.dtbo`: Ov5645 camera (see :ref:`boards/i300-pumpkin:Cameras` section)

Limitations
-----------

The software image has the current limitations:

* OpenGL is not yet supported. Only OpenGL ES, OpenCL and Vulkan are supported.
* X11 is not available (because of the lack of OpenGL)
* CSI Camera / NAND Storage / HW accelerated video encode are not yet supported

.. _i300B / MT8362A SoC: https://www.mediatek.com/products/richIot/mt8362b
.. _i300A / MT8362A SoC: https://www.mediatek.com/products/richIot/mt8362a
.. _Seeed: https://www.seeedstudio.com/
.. _Pumpkin EVK - Smart Audio Edition: https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Audio-Edition-p-4263.html
.. _Pumpkin EVK - Smart Hub Edition: https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Hub-Edition-p-4262.html
.. _7-inch Raspberry Pi touch display: https://www.raspberrypi.org/products/raspberry-pi-touch-display/
