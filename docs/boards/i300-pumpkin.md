# i300 Pumpkin Board

The i300 pumpkin board comes in two variants:
* [i300B / MT8362A SoC](https://www.mediatek.com/products/richIot/mt8362b), 1GB DRAM, 4GB eMMC
* [i300A / MT8362A SoC](https://www.mediatek.com/products/richIot/mt8362a), 2GB DRAM, 8GB eMMC

The i300 pumpkin board is using the [i300 platform](../platforms/i300.md).

## Purchasing

The i300 pumpkin boards are available on [Seeed](https://www.seeedstudio.com/):
* [Pumpkin EVK - Smart Audio Edition](https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Audio-Edition-p-4263.html) (i300B / MT8362)
* [Pumpkin EVK - Smart Hub Edition](https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Hub-Edition-p-4262.html) (i300A / MT8362)

## Building

You can build an image for the i300 pumpkin board by setting the
`MACHINE` variable in your `local.conf` to `i300a-pumpkin` or `i300b-pumpkin`.

	MACHINE=i300a-pumpkin
	MACHINE=i300b-pumpkin

For full build instructions, please refer to the top level `README.md`.

## Display

The i300A Pumpkin support HDMI and the [7-inch Raspberry Pi touch display](https://www.raspberrypi.org/products/raspberry-pi-touch-display/).

HDMI is working by default if you set `MACHINE=i300a-pumpkin`. If you wish to use the 7-inch Raspberry Pi touch display, you need to add the following to your `local.conf`:

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		rpi-display.dtbo \
	"

Warning: Adding `rpi-display.dtbo` to KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD requires you to plug the 7-inch Raspberry Pi touch display to the i300A pumpkin board. Failing to connect it will prevent HDMI from working. If you only want HDMI, you should not add `rpi-display.dtbo`.

The i300A Pumpkin also supports the AVD-TT70WS-CN-134-A touch display. If you wish to use it, you need to add the following to your `local.conf`:

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		avd-tt70ws-cn-134-a.dtbo \
	"

## Audio

By default, the pumpkin board ouputs audio on the jack connector.

It is possible to output the audio on the MT6392 PMIC mono lineout as well. To enable this output, you will need to change one of the alsa settings using the following command:

	$ amixer set -c mtsndcard 'Codec_Loopback_Select',0 CODEC_LOOPBACK_DMIC_TO_SPK

Setting any loopback to the speaker will work.

In order to disable this audio output, simply disable the loopback by using the following command:

	$ amixer set -c mtsndcard 'Codec_Loopback_Select',0 CODEC_LOOPBACK_NONE

## Device-Tree Overlays (DTBO)

The following Device-Tree Overlays are supported:
* `rpi-display.dtbo`: Raspberry Pi touch display (see [Displays](#displays) section)
* `panel-avd-tt70ws-cn-134-a.dtbo`: AVD-TT70WS-CN-134-A touch display module (see [Displays](#displays) section)

## Limitations

The software image has the current limitations:
* OpenGL, and Vulkan are not yet supported. Only OpenGL ES and OpenCL are supported.
* X11 is not available (because of the lack of OpenGL)
* CSI Camera / NAND Storage / HW accelerated video encode are not yet
supported
