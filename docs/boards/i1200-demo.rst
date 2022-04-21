i1200 demo Board
==================

Audio
-----

Playback
^^^^^^^^

By default, the i1200 demo board ouputs audio on the jack connector(headset).

The following command is an example that will start a music playback of a wav file that is already on the device.

.. prompt:: bash $

        aplay playback_file.wav

It is possible to play audio using the i2s out device present on the board instead.
In this case, the device will need to be specified explicitly as follows:

.. prompt:: bash $

        aplay -D i2s_out playback_file.wav


Capture
^^^^^^^

By default, the i1200 demo board captures audio using the jack microphone.

The following command is an example that will start a mono record with a sampling rate of 48kHz and a signed 32bits bit format:

.. prompt:: bash $

        arecord -c 1 -r 48000 -f s32_le recorded_file.wav


It is possible to record using the 8 PDM mics or i2s in device present on the board.
In this case, the device will need to be specified explicitly as follows:

To use dmic:

.. prompt:: bash $

        arecord -D dmic -c 2 -r 48000 -f s32_le recorded_file.wav

To use i2s_in:

.. prompt:: bash $

        arecord -D i2s_in -c 2 -r 48000 -f s32_le recorded_file.wav

40 pins header configuration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Here is the pin configuration to use the audio devices present on the 40 pins header:

+-------------+---------------+
| Header pin  | Function      |
+=============+===============+
| 7           | EXT_AUDIO_P3V3|
+-------------+---------------+
| 19          | I2SO1_MCK     |
+-------------+---------------+
| 21          | I2SO1_BCK     |
+-------------+---------------+
| 23          | I2SO1_WS      |
+-------------+---------------+
| 25          | I2SO1_D0      |
+-------------+---------------+
| 27          | I2SO1_D1      |
+-------------+---------------+
| 29          | I2SO1_D2      |
+-------------+---------------+
| 31          | I2SO1_D3      |
+-------------+---------------+
| 18          | DMIC1_SCK     |
+-------------+---------------+
| 20          | DMIC1_DAT     |
+-------------+---------------+
| 26          | DMIC2_DAT     |
+-------------+---------------+
| 30          | DMIC3_SCK     |
+-------------+---------------+
| 32          | DMIC3_DAT     |
+-------------+---------------+
| 36          | DMIC4_SCK     |
+-------------+---------------+
| 38          | DMIC4_DAT     |
+-------------+---------------+



I2S devices can be connected to these pins and, provided they don’t need any configuration and as a consequence don’t need any codec, they will work without any additional modifications.
Moreover, a 3V3 alimentation is available at pin 7 for all devices that need it.
