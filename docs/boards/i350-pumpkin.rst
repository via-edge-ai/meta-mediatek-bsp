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

Audio
-----

Playback
^^^^^^^^

By default, the i350 Pumpkin board ouputs audio on the jack connector.

The following command is an example that will start a music playback of a wav file that is already on the device:

.. prompt:: bash $

	aplay playback_file.wav

It is possible to use TDM out instead provided you have the corresponding device connected on the 40 pins header.
In this case, the device will need to be specified explicitly as follows:

.. prompt:: bash $

	aplay -D tdm_out playback_file.wav

It is also possible to switch to I2S out provided you have the corresponding device connected on the 40 pins header. In order to do that, you don't need to specify a device but you will need to change several alsa settings using the following commands:

In order to disable the headset output and enable I2S out:

.. prompt:: bash $

	amixer sset -c mtsndcard 'I2S O03_O04',0 on
	amixer sset -c mtsndcard 'INT ADDA O03_O04',0 off

In order to switch back to Headset out:

.. prompt:: bash $

	amixer sset -c mtsndcard 'I2S O03_O04',0 off
	amixer sset -c mtsndcard 'INT ADDA O03_O04',0 on

Capture
^^^^^^^

By default, the i350 Pumpkin board captures audio using the jack microphone.

The following command is an example that will start a mono record with a sampling rate of 48kHz and a signed 32bits bit format:

.. prompt:: bash $

	arecord -c 1 -r 48000 -f s32_le recorded_file.wav


Another possibility is using the Analog mics (that you will have to plug on the board first).
In that case, you will need to switch from the jack mic to the analog mic using the following command:

.. prompt:: bash $

	amixer sset -c mtsndcard 'Audio_MicSource1_Setting',0 ADC1

Then use a similar command as for jack mic:

.. prompt:: bash $

	arecord -c 2 -r 48000 -f s32_le recorded_file.wav

In order to switch back to jack mic, use the following command:

.. prompt:: bash $

	amixer sset -c mtsndcard 'Audio_MicSource1_Setting',0 ADC2

40 pins header configuration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Here is the pin configuration to use the audio devices present on the 40 pins header:

+-------------+---------------+
| Header pin  | Function      |
+=============+===============+
| 12          | TDM_LRCK      |
+-------------+---------------+
| 16          | TDM_MCK       |
+-------------+---------------+
| 37          | TDM_BCK       |
+-------------+---------------+
| 35          | TDM_DATA0     |
+-------------+---------------+
| 40          | TDM_DATA1     |
+-------------+---------------+
| 11          | TDM_DATA2     |
+-------------+---------------+
| 38          | TDM_DATA3     |
+-------------+---------------+
| 33          | I2S1_LRCK     |
+-------------+---------------+
| 32          | I2S1_MCK      |
+-------------+---------------+
| 29          | I2S1_BCK      |
+-------------+---------------+
| 31          | I2S1_DO       |
+-------------+---------------+

USB audio
^^^^^^^^^

USB audio is supported on this board. Simply plug an USB audio device (an USB headset for example) and check its id or name before playing or recording something.

In order to play a wav file:

.. prompt:: bash $

        # List the playback devices
        aplay -l
        # If USB card id is 1 and its playback device id is 0,
        # using the following command (forcing the framerate
        # to 48HHz)
        aplay -D plughw:1,0 -r 48000 playback_file.wav

In order to record a wav file:

.. prompt:: bash $

        # List the capture devices
        arecord -l
        # If USB card id is 1 and its capture device id is 0,
        # using the following command (forcing the framerate
        # to 48HHz)
        arecord -D plughw:1,0 -r 48000 -c 1 -f s32_le recorded_file.wav
