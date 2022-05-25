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

HDMI is working by default if you set `MACHINE=i300a-pumpkin`. If you wish to use the 7-inch Raspberry Pi touch display, the related overlay is `recipes-kernel/dtbo/mt8167-pumpkin/panel-raspberrypi.dts`

.. warning::

	Using `panel-raspberrypi.dtbo` requires you to plug the
	7-inch Raspberry Pi touch display to the i300A pumpkin board.
	Failing to connect it will prevent HDMI from working.
	If you only want HDMI, you should not use this overlay.

The i300A Pumpkin also supports the AVD-TT70WS-CN-134-A touch display. If you wish to use it, the related overlay is `recipes-kernel/dtbo/mt8167-pumpkin/panel-avd-tt70ws-cn-134-a.dts`

Cameras
-------

The i300A Pumpkin board supports the following csi camera sensor:

* Omnivision ov5645

If you wish to use the ov5645 camera, the related overlay is `recipes-kernel/dtbo/mt8167-pumpkin/camera-ov5645.dts`

Audio
-----

Default Playback
^^^^^^^^^^^^^^^^

By default, the pumpkin board ouputs audio on the jack connector.

It is possible to output the audio on the MT6392 PMIC mono lineout as well. To enable this output, you will need to change one of the alsa settings using the following command:

.. prompt:: bash $

	amixer set -c mtsndcard 'Codec_Loopback_Select',0 CODEC_LOOPBACK_DMIC_TO_SPK

Setting any loopback to the speaker will work.

In order to disable this audio output, simply disable the loopback by using the following command:

.. prompt:: bash $

	amixer set -c mtsndcard 'Codec_Loopback_Select',0 CODEC_LOOPBACK_NONE

Default Capture
^^^^^^^^^^^^^^^

By default, the pumpkin board captures audio using 2 PDM microphones that must be plugged directly on the board.

The following command is an example that will start a stereo record with a sampling rate of 48kHz and a signed 32bits bit format:

.. prompt:: bash $

	arecord -c 2 -r 48000 -f s32_le recorded_file.wav

It is possible to record using the jack microphone instead. In order to switch to this input, you will need to change a couple of alsa settings using the following commands:

.. prompt:: bash $

	amixer set -c mtsndcard 'AIF TX Mux',0 'Analog MIC'
	amixer set -c mtsndcard 'Left PGA Mux',0 'CH1'

In this case, in order to record, the jack device will have to be specified as follows:

.. prompt:: bash $

	arecord -D hwjackmic -c 2 -r 48000 -f s32_le recorded_file.wav

Using the audio I2S inputs/outputs on the 40 pins header
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

At build time, it is possible to move from jack/pdm mics to the audio inputs/outputs available on the 40 pins header.
In order to do so, you can set the `I300_PUMPKIN_AUDIO_CONF` variable in your `local.conf` to `i2s`.

.. code::

	I300_PUMPKIN_AUDIO_CONF=i2s

With this configuration, it is currently possible to use a 2channels I2S in device (named I2S2) for the capture and and 8 channels I2S out device (named I2S) for the playback.

.. warning::

	Please note that the SD card will be disabled in this case as the SD card share its pins with the I2S 8 channels out device.

Here is the pin configuration to use on the 40 pins header:

+-------------+---------+---------------+
| Header pin  | GPIO    | Function      |
+=============+=========+===============+
| 19          | GPIO51  | I2S2_MCK      |
+-------------+---------+---------------+
| 40          | GPIO55  | I2S2_BCK      |
+-------------+---------+---------------+
| 7           | GPIO2   | I2S2_LRCK     |
+-------------+---------+---------------+
| 23          | GPIO49  | I2S2_DI       |
+-------------+---------+---------------+
| 11          | GPIO25  | I2S_8CH_MCK   |
+-------------+---------+---------------+
| 13          | GPIO73  | I2S_8CH_BCK   |
+-------------+---------+---------------+
| 37          | GPIO72  | I2S_8CH_LRCK  |
+-------------+---------+---------------+
| 22          | GPIO71  | I2S_8CH_DO1   |
+-------------+---------+---------------+
| 18          | GPIO70  | I2S_8CH_DO2   |
+-------------+---------+---------------+
| 15          | GPIO69  | I2S_8CH_DO3   |
+-------------+---------+---------------+
| 16          | GPIO68  | I2S_8CH_DO4   |
+-------------+---------+---------------+

I2S devices can be connected to these pins and, provided they don't need any configuration and as a consequence don't need any codec, they will work without any additional modifications.
Regarding the I2S 8 channels out device, we can imagine using 4 x 2 channels I2S devices. In that case, the devices will share the same MCK, LRCK and BCK but will have a different data line each (DO1, DO2, DO3 or DO4).

MT7668 wireless chipset
------------------------

The i300 Pumpkin board is integrating
a MT7668 wireless chipset.
By default the chipset comes with no MAC or BD address. Please refer to the
MT7668 wireless chipset documentation to see how to
program a MAC or BD address.

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
* NAND Storage is not yet supported

.. _i300B / MT8362A SoC: https://www.mediatek.com/products/richIot/mt8362b
.. _i300A / MT8362A SoC: https://www.mediatek.com/products/richIot/mt8362a
.. _Seeed: https://www.seeedstudio.com/
.. _Pumpkin EVK - Smart Audio Edition: https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Audio-Edition-p-4263.html
.. _Pumpkin EVK - Smart Hub Edition: https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Hub-Edition-p-4262.html
.. _7-inch Raspberry Pi touch display: https://www.raspberrypi.org/products/raspberry-pi-touch-display/
