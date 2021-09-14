i350 EVK Board
================

The EVK is a development board made by MediaTek.

Building
--------

You can build an image for the Innocomm SB35 board by setting the
`MACHINE` variable in your `local.conf` to `i350-evk`.

.. code::

	MACHINE=i350-evk

For full build instructions, please refer to the top level `README.md`.


Ethernet and HDMI support
-------------------------

Ethernet and HDMI are sharing the same pins, which means only one is
available at a time.

Ethernet
^^^^^^^^

To use Ethernet, make sure you select the `LAN` feature on the `SW2101` switch.
In addition because Ethernet is disabled by default in the kernel device-tree,
you will also need to load an overlay to enable Ethernet.

If you wish to use Ethernet, you need to add the following to your `local.conf`:

.. code::

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD:i350-evk += " \
		net-ethernet.dtbo \
	"

HDMI
^^^^

To use HDMI, make sure you select the `DPI` feature on the `SW2101` switch. By
default HDMI is enabled in the kernel device-tree. Make sure the Ethernet
overlay is not loaded otherwise HDMI would get disabled
(see :ref:`boards/i350-evk:ethernet` section).


MT7663 wireless chipset
------------------------

The i350 EVK board is integrating
a :ref:`platforms/mt7663:MT7663 wireless chipset`.

Device-Tree Overlays (DTBO)
---------------------------

The following Device-Tree Overlays are supported:

* `net-ethernet.dtbo`: Enable support for Ethernet (see :ref:`boards/i350-evk:ethernet` section)

.. _7-inch Raspberry Pi touch display: https://www.raspberrypi.org/products/raspberry-pi-touch-display/

FTDI board control
------------------

The i350 EVK has two FTDI chips connected to the UART0 (CON461) port
and to the UART1 (CON462) port. They are both able to control the
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
