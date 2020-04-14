# Development

## TFTP boot

During kernel development, it can be useful to not have to reflash the board
every time you generate a new kernel image. We support TFTP kernel boot via
`u-boot`.
Only the client side is described here. The installation and configuration
of a TFTP server is not described in this document.

### Enabling TFTP boot

TFTP boot can be enabled from withing U-Boot shell or from Linux userspace.

#### Linux

To be able to modify the `u-boot` environment from within the Linux userspace,
you need to make sure you have included `u-boot-env` and `u-boot-fw-utils` as
part of your image.

For instance you can add the following to your `local.conf` file:

	IMAGE_INSTALL_append = " \
		u-boot-env \
		u-boot-fw-utils \
	"

In the Linux userspace you can run the following command to enable TFTP boot:

	$ fw_setenv force_tftpboot 1

#### U-Boot

To enable TFTP boot from within the `u-boot` shell, you can run the following
command:

	=> setenv force_tftpboot 1

### Disabling TFTP boot

Once you want to disable TFTP boot and start booting again the kernel present
in the `kernel` partition of the mass storage, you can run one of the following
commands:

#### Linux

	$ fw_setenv force_tftpboot 0

#### U-Boot

	=> setenv force_tftpboot 0

### Network configuration

U-Boot is using staticly defined IPs to connect to the TFTP server. DHCP is
not supported yet. You can see the configuration below:

|  Machine              |  IP              |
|-----------------------|------------------|
| Client (U-Boot)       | 192.168.96.1/24  |
| Server (Your machine) | 192.168.96.20/24 |
