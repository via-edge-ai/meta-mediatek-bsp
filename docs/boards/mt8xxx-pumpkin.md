# MT8xxx Pumpkin Board

The i300 pumpkin board comes in two variants:
* [MT8516 SoC](https://www.mediatek.com/products/richIot/mt8362b), 1GB DRAM, 4GB eMMC
* [MT8167 SoC](https://www.mediatek.com/products/richIot/mt8362a), 2GB DRAM, 8GB eMMC

The i300 pumpkin board is using the [i300 platform](../platforms/i300.md).

## Purchasing

The i300 pumpkin boards are available on [Seeed](https://www.seeedstudio.com/):
* [Pumpkin EVK - Smart Audio Edition](https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Audio-Edition-p-4263.html) (MT8516)
* [Pumpkin EVK - Smart Hub Edition](https://www.seeedstudio.com/Pumpkin-Evaluation-Kit-Smart-Hub-Edition-p-4262.html) (MT8167)

## Building

You can build an image for the i300 pumpkin board by setting the
`MACHINE` variable in your `local.conf` to `mt8167-pumpkin` or `mt8516-pumpkin`.

	MACHINE=mt8167-pumpkin
	MACHINE=mt8516-pumpkin

For full build instructions, please refer to the top level `README.md`.

## Display

The MT8167 Pumpkin support HDMI and the [7-inch Raspberry Pi touch display](https://www.raspberrypi.org/products/raspberry-pi-touch-display/).

HDMI is working by default if you set `MACHINE=mt8167-pumpkin`. If you wish to use the 7-inch Raspberry Pi touch display, you need to add the following to your `local.conf`:

	MACHINE_FEATURES_append = " screen "

Warning: Adding `screen` as MACHINE_FEATURES requires you to plug the 7-inch Raspberry Pi touch display to the i300A pumpkin board. Failing to connect it will prevent HDMI from working. If you only want HDMI, you should not add `screen`.

## Device-Tree Overlays (DTBO)

The following Device-Tree Overlays are supported:
* `rpi-display.dtbo`: Raspberry Pi touch display (see [Displays](#displays) section)

## Limitations

The software image has the current limitations:
* OpenGL, OpenCL, and Vulkan are not yet supported. Only OpenGL ES is supported.
* X11 is not available (because of the lack of OpenGL)
* CSI Camera / NAND Storage / HW accelerated video encode / decode are not yet
supported
