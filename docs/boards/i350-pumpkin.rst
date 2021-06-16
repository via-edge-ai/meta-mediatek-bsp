i350 Pumpkin Board
==================

Purchasing
----------

The i350 pumpkin board is made by `OLogic`_. To request a pumpkin i350, please
`contact OLogic`_.

.. _OLogic: https://ologicinc.com/
.. _contact OLogic: https://ologicinc.com/contact/

Building
--------

You can build an image for the i350 pumpkin board by setting the
`MACHINE` variable in your `local.conf` to `i350-pumpkin`.

.. code::

	MACHINE=i350-pumpkin

For full build instructions, please refer to the top level `README.md`.

MT7663 wireless chipset
------------------------

The i350 EVK board is integrating
a :ref:`platforms/mt7663:MT7663 wireless chipset`.

FTDI board control
------------------

The FTDI chip connected to the DEBUG (CN5) port is able to control the
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

.. _AIoT tools manual: https://mediatek.gitlab.io/aiot/bsp/aiot-tools/
.. _configure: https://mediatek.gitlab.io/aiot/bsp/aiot-tools/#configuration-of-the-ftdi-chip
.. _control: https://mediatek.gitlab.io/aiot/bsp/aiot-tools/#rity-board
