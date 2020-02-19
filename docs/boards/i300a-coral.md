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

## Limitations

Most peripherals provided by the board are not supported.
