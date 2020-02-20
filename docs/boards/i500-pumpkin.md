# i500 Pumpkin Board

The i500 pumpkin is using the [i500 platform](../platforms/i500.md).

## Building

You can build an image for the i500 pumpkin board by setting the
`MACHINE` variable in your `local.conf` to `i500-pumpkin`.

	MACHINE=i500-pumpkin

For full build instructions, please refer to the top level `README.md`.

## Limitations

The software image has the following limitations:
* CSI (Camera), GPU (HW acceleration), MDP,
HW accelarated video encode / decode, keypad are not yet supported
