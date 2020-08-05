# i500 Pumpkin Board

The i500 pumpkin is using the [i500 platform](../platforms/i500.md).

## Building

You can build an image for the i500 pumpkin board by setting the
`MACHINE` variable in your `local.conf` to `i500-pumpkin`.

	MACHINE=i500-pumpkin

For full build instructions, please refer to the top level `README.md`.

## Displays

The i500 Pumpkin board supports the following display:
* URT UMO 9465MD-T

If you wish to use the DSI display, you need to add the following to your
`local.conf`:

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		panel-urt-umo9465md.dtbo \
	"

Warning: Adding the dtbo to KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD requires you to
plug the DSI display to the pumpkin board. Failing to connect it will
prevent HDMI from working. If you only want HDMI, you should not add the dtbo
to your `local.conf`.

## Device-Tree Overlays (DTBO)

The following Device-Tree Overlays are supported:
* `panel-urt-umo-9465md.dtbo`: URT UMO display (see [Displays](#displays) section)

## Limitations

The software image has the following limitations:
* CSI (Camera), GPU (HW acceleration), MDP,
HW accelarated video encode / decode, keypad are not yet supported
