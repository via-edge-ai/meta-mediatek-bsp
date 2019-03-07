# MediaTek BSP layer

This layer provides a minimal BSP and rootfs for booting the MediaTek boards.

The following machines are supported:
	* pumpkin-mt8516: Pumpkin board with MediaTek MT8516.

## Building

    $ mkdir rich-iot; cd rich-iot
    $ repo init -u git@gitlab.com:baylibre/rich-iot/manifest.git
    $ repo sync
    $ export TEMPLATECONF=${PWD}/src/meta-mediatek-bsp/conf/
    $ source src/poky/oe-init-build-env
    $ bitbake pumpkin-image

## Flashing

### Flashing everything

    $ cd rich-iot/build/tmp/deploy/images/pumpkin-mt8516
    $ ./flashimage.py
                                     Checking image
    --------------------------------------------------------------------------------
                                  MBR_EMMC : PASS
                                    lk.img : PASS
                                    tz.img : PASS
                                  fitImage : PASS
         pumpkin-image-pumpkin-mt8516.ext4 : PASS

                                     Start flashing
    --------------------------------------------------------------------------------
    Waiting for DA mode
    .

Once you see *Waiting for DA mode*:
1) press the *reset* and *volume down* buttons **simultaneously**
2) then release only the *reset* button
3) release the *volume* button once you see that the image is getting flashed.

### Flash only one partition

To flash just one partition, you can run the following command:

    $·cd·rich-iot/build/tmp/deploy/images/pumpkin-mt8516
    $ fastboot flash [PARTITION] [FILE]
    $ fastboot reboot

[PARTITION] should be replaced with one of the following:
    - *BOOTIMG1*: for flashing the Linux Kernel (fitImage).
    - *ROOTFS1*: for flashing the root filesystem (pumpkin-image-pumpkin-mt8516.ext4).

For example, the commands to flash the kernel are:

    $·cd·rich-iot/build/tmp/deploy/images/pumpkin-mt8516
    $ fastboot flash BOOTIMG1 fitImage
    $ fastboot reboot

The commands to flash the root filesystem are:

    $·cd·rich-iot/build/tmp/deploy/images/pumpkin-mt8516
    $ fastboot flash ROOTFS1 pumpkin-image-pumpkin-mt8516.ext4
    $ fastboot reboot

## Connecting to the board

You can connect to the board via the serial console exported on the Micro-B USB connector:

    $ picocom -b 921600 /dev/ttyUSB0
    root@pumpkin-mt8516:~#

---
The image is running ssh (dropbear) and avahi in order to make it easily accessible through a Network Interface:

    $ ssh root@pumpkin-mt8516.local
    root@pumpkin-mt8516:~#

The image is also configuring a USB Gadget on the USB Type-C connector that can be used as a Network Interface to connect to the board.

## Configuring the Wi-Fi

To configure the board to connect to your Wi-Fi AP you need to run the following command:

    root@pumpkin:~# wpa_passphrase [SSID] [PASSPHRASE] >> /etc/wpa_supplicant.conf

This command needs to be typed only once, the Wi-Fi network will be saved in
the *wpa_supplicant.conf* file. [SSID] needs to be replaced with your
Wi-Fi SSID, and [PASSPHRASE] needs to be replaced with the passphrase
associated to that SSID.

## local.conf options

Options available in `local.conf` to modify the BSP:

* `USB_GADGET_FUNCTION`: This option allows to choose the USB gadget used
	to expose an Ethernet interface over USB. Available choices are: `ecm` (default),
	`rndis`.

## Known issues

* On Windows 10, and possibly other version of windows, the ECM gadget is
	not well supported, to fix it you can set the USB_GADGET_FUNCTION to `rndis`,
	see `local.conf options` for more details.
