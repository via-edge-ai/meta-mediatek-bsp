# MediaTek BSP layer

This layer provides a minimal BSP and rootfs for booting the MediaTek boards.

The following machines are supported:
* i300a-coral: Google Coral board.
* i300a-pumpkin: Pumpkin board with MediaTek i300a.
* i300b-pumpkin: Pumpkin board with MediaTek i300b.
* i500-pumpkin: Pumpkin board with MediaTek i500.
* i500-evb: Evaluation board for MediaTek i500.

The machine by default is set to i300b-pumpkin. See [local.conf Options](#localconf-options) on how to change it.

## Building

    $ mkdir rich-iot; cd rich-iot
    $ repo init -u git@gitlab.com:baylibre/rich-iot/manifest.git
    $ repo sync
    $ export TEMPLATECONF=${PWD}/src/meta-mediatek-bsp/conf/
    $ source src/poky/oe-init-build-env
    $ bitbake mtk-image

## Flashing

### Flashing everything

    $ cd rich-iot/build/tmp/deploy/images/i300b-pumpkin
    $ ./flashimage.py
                                     Checking image
    --------------------------------------------------------------------------------
                                  MBR_EMMC : PASS
                                   bl2.img : PASS
                            u-boot-env.bin : PASS
                                   fip.bin : PASS
                                  fitImage : PASS
              mtk-image-i300b-pumpkin.ext4 : PASS

                                     Start flashing
    --------------------------------------------------------------------------------
    Waiting for DA mode
    .

Once you see *Waiting for DA mode*:
1) press the *reset* and *volume up* buttons **simultaneously**
2) then release only the *reset* button
3) release the *volume up* button once you see that the image is getting flashed.

### Flash only one partition

To flash just one partition, you can run the following command:

    $ cd rich-iot/build/tmp/deploy/images/i300b-pumpkin
    $ fastboot flash [PARTITION] [FILE]
    $ fastboot continue

[PARTITION] should be replaced with one of the following:
- *bootloaders*: for flashing the bootloaders (bl31, OP-TEE, and u-boot)
- *kernel*: for flashing the Linux Kernel (fitImage).
- *rootfs*: for flashing the root filesystem (mtk-image-i300b-pumpkin.ext4).

For example, the commands to flash the kernel are:

    $ cd rich-iot/build/tmp/deploy/images/i300b-pumpkin
    $ fastboot flash kernel fitImage
    $ fastboot continue

The commands to flash the bootloaders are:

    $ cd rich-iot/build/tmp/deploy/images/i300b-pumpkin
    $ fastboot flash bootloaders fip.bin
    $ fastboot continue

The commands to flash the root filesystem are:

    $ cd rich-iot/build/tmp/deploy/images/i300b-pumpkin
    $ fastboot flash rootfs mtk-image-i300b-pumpkin.ext4
    $ fastboot continue

## Connecting to the board

You can connect to the board via the serial console exported on the Micro-B USB connector:

    $ picocom -b 921600 /dev/ttyUSB0
    root@i300b-pumpkin:~#

---
The image is running ssh (dropbear) and avahi in order to make it easily accessible through a Network Interface:

    $ ssh root@i300b-pumpkin.local
    root@i300b-pumpkin:~#

The image is also configuring a USB Gadget on the USB Type-C connector that can be used as a Network Interface to connect to the board.

## Configuring the Wi-Fi

To configure the board to connect to your Wi-Fi AP you need to run the following command:

    root@i300b-pumpkin:~# wpa_passphrase [SSID] [PASSPHRASE] >> /etc/wpa_supplicant.conf

This command needs to be typed only once, the Wi-Fi network will be saved in
the *wpa_supplicant.conf* file. [SSID] needs to be replaced with your
Wi-Fi SSID, and [PASSPHRASE] needs to be replaced with the passphrase
associated to that SSID.

## local.conf options

Options available in `build/conf/local.conf` to modify the BSP:

* `MACHINE`: This option defines the machine configuration used to generates
	the image. It is set by default to `i300b-pumpkin` but can be changed to
	any of the machine available.

* `USB_GADGET_FUNCTION`: This option allows to choose the USB gadget used
	to expose an Ethernet interface over USB. Available choices are: `ecm` (default),
	`rndis`.

* `KERNEL_DEVICETREE_OVERLAYS_AUTOLOAD`: List of kernel device-tree overlays
	that should be automatically loaded and merged by u-boot to the main dtb
	before jumping into the kernel.

## DISTRO_FEATURES

The Layer is defining the following custom DISTRO_FEATURES:
* `optee`: The OP-TEE Trusted Execution Environment will be built into the image.

These DISTRO_FEATURES can be added to the image by setting the variable in [local.conf](#localconf-options).

## MACHINE_FEATURES

The layer is defining the following custom MACHINE_FEATURES:
* `vesper-hat`: Enable the use of the vesper audio hat for the i300 pumpkin board.

## Known issues

* On Windows 10, and possibly other version of windows, the ECM gadget is
	not well supported, to fix it you can set the USB_GADGET_FUNCTION to `rndis`,
	see `local.conf options` for more details.
