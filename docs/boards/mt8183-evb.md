# MT8183 Evaluation Board

The MT8183 EVB is using the [i500 platform](../platforms/i500.md).

## Building

You can build an image for the MT8183 Evaluation Board by setting the
`MACHINE` variable in your `local.conf` to `mt8183-evb`.

	MACHINE=mt8183-evb

For full build instructions, please refer to the top level `README.md`.

## Displays

The MT8183 Evaluation board supports three differents displays:
* Sharp NT35532 (Red display)
* TPV OTM1901A (Green display)
* Truly R63350A (Yellow display)

The default display when building with `MACHINE=mt8183-evb` is `Sharp NT35532`.
If your display on your evaluation board is different, you can add support to
your display through device-tree overlays. In order to do so, you can add one
of the following to the `KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD` in your
`local.conf`:
* Green display: `panel-tpv-otm1901a.dtbo`
* Yellow display: `panel-truly-r63350a.dtbo`

Examples:

	KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD += " \
		panel-tpv-otm1901a.dtbo \
	"

## Device-Tree Overlays (DTBO)

The following Device-Tree Overlays are supported:
* `panel-tpv-otm1901a.dtbo`: Green display (see [Displays](#displays) section)
* `panel-truly-r63350a.dtbo`: Yellow display (see [Displays](#displays) section)

## Limitations

The software image has the following limitations:
* CSI (Camera), GPU (HW acceleration), MDP,
HW accelarated video encode / decode, keypad are not yet supported
