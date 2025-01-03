i500 Pumpkin Board
==================

The `i500 pumpkin`_ is using the :ref:`platforms/i500:i500 (MT8385)` platform.

.. _i500 pumpkin: https://ologicinc.com/portfolio/mediateki500/

Purchasing
----------

The i500 pumpkin board is made by `OLogic`_. To request a pumpkin i500, please
`contact OLogic`_.

.. _OLogic: https://ologicinc.com/
.. _contact OLogic: https://ologicinc.com/contact/

Building
--------

You can build an image for the i500 pumpkin board by setting the
`MACHINE` variable in your `local.conf` to `i500-pumpkin`.

.. code::

	MACHINE=i500-pumpkin

For full build instructions, please refer to the top level `README.md`.

Displays
--------

The i500 Pumpkin board supports the following display:

* URT UMO 9465MD-T
* `7-inch Raspberry Pi touch display`_

If you wish to use the URT UMO 9465MD-T display,
the related overlay is `recipes-kernel/dtbo/mt8183-pumpkin/panel-urt-umo9465md.dts`

To use the 7-inch Raspberry Pi touch display, the related overlay is
`recipes-kernel/dtbo/mt8183-pumpkin/panel-raspberrypi.dts`

.. warning::

	Using one of these overlay require you to plug the DSI display
	to the pumpkin board. Failing to connect it will prevent HDMI from
	working. If you only want HDMI, you should not use these overlay.

Cameras
-------

The i500 Pumpkin board supports the following csi camera configs:

* Onsemi AR0330 (single or dual) raw sensors
* Onsemi AP1302 (single or dual) ISPs + AR0330 / AR0144 (single or dual) sensors
* Onsemi AP1302 (single) ISP + AR1335 (single) sensor

Onsemi AR0330 (single or dual) raw sensors
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

You need to use the following dtbo depending the number of AR0330 sensors
you have:

* camera-ar0330-single.dtbo : one AR0330
* camera-ar0330-dual.dtbo   : two AR0330

For example, you should use `recipes-kernel/dtbo/mt8183-evb/camera-ar0330-single.dts`
if you have only one AR0330

Onsemi AP1302 (single or dual) ISPs + AR0330 / AR0144 (single or dual) sensors
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Two AP1302 ISPs is supported, the first ISP can support
up to two AR0330 sensors, while the second ISP can support up to
two AR0144 sensors.

Currently, only the following combinations are supported:

* camera-ap1302-ar0330-single.dtbo : one AP1302 + one AR0330
* camera-ap1302-ar0144-single.dtbo : one AP1302 + one AR0144
* camera-ap1302-ar0144-dual.dtbo : one AP1302 + dual AR0144
* camera-ap1302-ar0330-single-ar0144-single.dtbo : one AP1302 + one AR0330 and one AP1302 + one AR0144
* camera-ap1302-ar0330-single-ar0144-dual.dtbo : one AP1302 + one AR0330 and one AP1302 + dual AR0144

For example, you should use `recipes-kernel/dtbo/mt8183-evb/camera-ap1302-ar0330-single.dts`
if you have one AP1302 + one AR0330 combination:

Onsemi AP1302 (single) ISP + AR1335 (single) sensor
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The second ISP (which is capable of supporting AR0144 sensor) is capable
of supporting AR1335 sensor too.

To load the dtbo corresponding to AP1302 + AR1335 single, you should use
`recipes-kernel/dtbo/mt8183-evb/camera-ap1302-ar1335-single.dts`

Audio
-----

Playback
^^^^^^^^

By default, the i500 Pumpkin board ouputs audio on the jack connector.

It is possible to output the audio on the 2 channels I2S out device present on the 40 pins header instead. To switch to this output, you will need to change several alsa settings using the following commands:

.. prompt:: bash $

	amixer set -c mt8183mt6358 'ADDA_DL_CH1 DL1_CH1',0 off
	amixer set -c mt8183mt6358 'ADDA_DL_CH2 DL1_CH2',0 off
	amixer set -c mt8183mt6358 'I2S1_CH1 DL1_CH1',0 on
	amixer set -c mt8183mt6358 'I2S1_CH2 DL1_CH2',0 on

In order to move back to jack output, simply put back the original settings by using the following commands:

.. prompt:: bash $

	amixer set -c mt8183mt6358 'ADDA_DL_CH1 DL1_CH1',0 on
	amixer set -c mt8183mt6358 'ADDA_DL_CH2 DL1_CH2',0 on
	amixer set -c mt8183mt6358 'I2S1_CH1 DL1_CH1',0 off
	amixer set -c mt8183mt6358 'I2S1_CH2 DL1_CH2',0 off

Capture
^^^^^^^

By default, the i500 Pumpkin board captures audio using the jack microphone.

The following command is an example that will start a mono record with a sampling rate of 48kHz and a signed 32bits bit format:

.. prompt:: bash $

	arecord -c 1 -r 48000 -f s32_le recorded_file.wav

It is possible to record using the 4 channels I2S in device present on the 40 pins header instead.
In this case, the 2ch I2S device will have to be specified as follows:

.. prompt:: bash $

	arecord -D 4ch_mic -c 4 -r 48000 -f s32_le recorded_file.wav

Note that it is possible to record 1, 2 or 4 channels using this device.

40 pins header configuration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Here is the pin configuration to use the audio devices present on the 40 pins header:

+-------------+---------------+
| Header pin  | Function      |
+=============+===============+
| 32          | I2S2_MCK      |
+-------------+---------------+
| 29          | I2S2_BCK      |
+-------------+---------------+
| 33          | I2S2_LRCK     |
+-------------+---------------+
| 38          | I2S2_DI       |
+-------------+---------------+
| 31          | I2S2_DI2      |
+-------------+---------------+
| 11          | I2S1_MCK      |
+-------------+---------------+
| 12          | I2S1_BCK      |
+-------------+---------------+
| 35          | I2S1_LRCK     |
+-------------+---------------+
| 40          | I2S1_DO       |
+-------------+---------------+

I2S devices can be connected to these pins and, provided they don't need any configuration and as a consequence don't need any codec, they will work without any additional modifications.
Regarding the I2S 4 channels in device, we can imagine using 2 x 2 channels I2S devices. In that case, the devices will share the same MCK, LRCK and BCK but will have a different data line each (DI or DI2).

MT7668 wireless chipset
------------------------

The i500 Pumpkin board is integrating
a MT7668 wireless chipset.
By default the chipset comes with no MAC or BD address. Please refer to the
MT7668 wireless chipset documentation to see how to
program a MAC or BD address.

Device-Tree Overlays (DTBO)
---------------------------

The following Device-Tree Overlays are supported:

* `panel-raspberrypi.dtbo`: Raspberry Pi touch display (see :ref:`boards/i500-pumpkin:Displays` section)
* `panel-urt-umo-9465md.dtbo`: URT UMO display (see :ref:`boards/i500-pumpkin:Displays` section)
* `camera-ar0330-single.dtbo`: single AR0330CS camera (see :ref:`boards/i500-pumpkin:Cameras` section)
* `camera-ar0330-dual.dtbo`: dual AR0330CS cameras (see :ref:`boards/i500-pumpkin:Cameras` section)
* `camera-ap1302-ar0330-single.dtbo`: single AR0330CS camera with AP1302 ISP (see :ref:`boards/i500-pumpkin:Cameras` section)
* `camera-ap1302-ar0144-single.dtbo`: single AR0144 camera with AP1302 ISP (see :ref:`boards/i500-pumpkin:Cameras` section)
* `camera-ap1302-ar0144-dual.dtbo`: dual AR0144 cameras with AP1302 ISP (see :ref:`boards/i500-pumpkin:Cameras` section)
* `camera-ap1302-ar0330-single-ar0144-single.dtbo`: single AR0330CS and single AR0144 cameras with dual AP1302 ISPs (see :ref:`boards/i500-pumpkin:Cameras` section)
* `camera-ap1302-ar0330-single-ar0144-dual.dtbo`: single AR0330CS and dual AR0144 cameras with dual AP1302 ISPs (see :ref:`boards/i500-pumpkin:Cameras` section)

FTDI board control
------------------

The FTDI chip connected to the DEBUG (CN7) port is able to control the
power (PWRKEY), reset (SYSRST), and download (KPCOL0) lines.

+----------------+-------------------+
| FTDI GPIO Line | Function          |
+================+===================+
| 0              | Power (PWRKEY)    |
+----------------+-------------------+
| 1              | Reset (SYSRST)    |
+----------------+-------------------+
| 2              | Download (KPCOL0) |
+----------------+-------------------+

.. note::

	By default the FTDI chip is not configured to use these 3 lines.
	Please refer to the `AIoT tools manual`_ to see how to `configure`_ and
	`control`_ these 3 lines.

Limitations
-----------

The software image has the following limitations:

* The 2 USB Type-A port (CN3) and the Ethernet port (CN5) can be used only when no cable is connected to the USB Type-C CN2 port. This is a hardware limitation that prevents USB host and USB device to be usable at the same time.

.. _7-inch Raspberry Pi touch display: https://www.raspberrypi.org/products/raspberry-pi-touch-display/
.. _AIoT tools manual: https://mediatek.gitlab.io/aiot/bsp/aiot-tools/
.. _configure: https://mediatek.gitlab.io/aiot/bsp/aiot-tools/#configuration-of-the-ftdi-chip
.. _control: https://mediatek.gitlab.io/aiot/bsp/aiot-tools/#rity-board
