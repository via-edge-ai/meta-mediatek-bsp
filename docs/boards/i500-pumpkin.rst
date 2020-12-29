i500 Pumpkin Board
==================

The i500 pumpkin is using the :ref:`platforms/i500:i500 (MT8385)` platform.

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

If you wish to use the URT UMO 9465MD-T display, you need to add
the following to your `local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		panel-urt-umo9465md.dtbo \
	"

To use the 7-inch Raspberry Pi touch display, you need to add the following to
your `local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		panel-raspberrypi.dtbo \
	"

Warning: Adding the dtbo to KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD requires you to
plug the DSI display to the pumpkin board. Failing to connect it will
prevent HDMI from working. If you only want HDMI, you should not add the dtbo
to your `local.conf`.

Cameras
-------

The i500 Pumpkin board supports the following csi camera sensor:

* Onsemi AR0330CS sensor
* Onsemi AR0330CS sensor + Onsemi AP1302 ISP

If you wish to use the AR0330CS camera, you need to add the following to your
`local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		camera-ar0330.dtbo \
	"

Or if you wish to use the AR0330CS camera with the AP1303 ISP, you need to add
the following to your `local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		camera-ap1302-ar0330.dtbo \
	"

MT7668 wireless chipset
------------------------

The i500 Pumpkin board is integrating
a :ref:`platforms/mt7668:MT7668 wireless chipset`.
By default the chipset comes with no MAC or BD address. Please refer to the
:ref:`platforms/mt7668:MT7668 wireless chipset` documentation to see how to
program a MAC or BD address.


Device-Tree Overlays (DTBO)
---------------------------

The following Device-Tree Overlays are supported:

* `panel-raspberrypi.dtbo`: Raspberry Pi touch display (see :ref:`boards/i500-pumpkin:Displays` section)
* `panel-urt-umo-9465md.dtbo`: URT UMO display (see :ref:`boards/i500-pumpkin:Displays` section)
* `camera-ar0330.dtbo`: AR0330CS camera (see :ref:`boards/i500-pumpkin:Cameras` section)
* `camera-ap1303-ar0330.dtbo`: AR0330CS camera with AP1302 ISP (see :ref:`boards/i500-pumpkin:Cameras` section)

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
	Please refer to the `RITY tools manual`_ to see how to `configure`_ and
	`control`_ these 3 lines.

Limitations
-----------

The software image has the following limitations:

* HW accelarated video encode / decode are not yet supported
* The 2 USB Type-A port (CN3) and the Ethernet port (CN5) can be used only when no cable is connected to the USB Type-C CN2 port. This is a hardware limitation that prevents USB host and USB device to be usable at the same time.

.. _7-inch Raspberry Pi touch display: https://www.raspberrypi.org/products/raspberry-pi-touch-display/
.. _RITY tools manual: https://baylibre.gitlab.io/rich-iot/tools/rity-tools/
.. _configure: https://baylibre.gitlab.io/rich-iot/tools/rity-tools/#configuration-of-the-ftdi-chip
.. _control: https://baylibre.gitlab.io/rich-iot/tools/rity-tools/#rity-board
