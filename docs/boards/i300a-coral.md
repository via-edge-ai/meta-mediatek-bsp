# i300A Coral Board

The Coral board (aka Coral dev board mini) is made by Google. The board is
designed for Machine Learning application with the integration of a
Tensor Processing Unit ASIC.

## Purchasing

The Google Coral board is available on [coral.ai](https://coral.ai/products/dev-board-mini/)

## Building

You can build an image for the Google Coral Board by setting the
`MACHINE` variable in your `local.conf` to `i300a-coral`.

	MACHINE=i300a-coral

For full build instructions, please refer to the top level `README.md`.

## Cameras

The Google Coral Board supports the following csi camera sensor:
* Omnivision ov5645

If you wish to use the ov5645 camera, you need to add the following to your
`local.conf`:

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		camera-ov5645.dtbo \
	"

## Device-Tree Overlays (DTBO)

The following Device-Tree Overlays are supported:
* `camera-ov5645.dtbo`: ov5645 camera (see [Cameras](#cameras) section)

## Limitations

Most peripherals provided by the board are not supported.
