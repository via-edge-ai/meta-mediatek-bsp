# Pumpkin Board

This layer provides a minimal BSP and rootfs for booting the Pumpkin board.

## Building

    $ mkdir pumpkin; cd pumpkin
    $ repo init -u git@gitlab.com:baylibre/pumpkin/manifest.git
    $ repo sync
    $ export TEMPLATECONF=${PWD}/meta-pumpkin/conf/
    $ source poky/oe-init-build-env
    $ bitbake pumpkin-image

## Flashing

### Flashing everything

    $ cd pumpkin/build/tmp/deploy/images/pumpkin
    $ ./flashimage.py
                                     Checking image
    --------------------------------------------------------------------------------
                                  MBR_EMMC : PASS
                                    lk.img : PASS
                                    tz.img : PASS
                                  fitImage : PASS
            pumpkin-image-pumpkin.ext4     : PASS

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

    $·cd·pumpkin/build/tmp/deploy/images/pumpkin
    $ fastboot flash [PARTITION] [FILE]
    $ fastboot reboot

[PARTITION] should be replaced with one of the following:
    - *BOOTIMG1*: for flashing the Linux Kernel (fitImage).
    - *ROOTFS1*: for flashing the root filesystem (pumpkin-image-pumpkin.ext4).

For example, the commands to flash the kernel are:

    $·cd·pumpkin/build/tmp/deploy/images/pumpkin
    $ fastboot flash BOOTIMG1 fitImage
    $ fastboot reboot

The commands to flash the root filesystem are:

    $·cd·pumpkin/build/tmp/deploy/images/pumpkin
    $ fastboot flash ROOTFS1 pumpkin-image-pumpkin.ext4
    $ fastboot reboot

## Connecting to the board

You can connect to the board via the serial console exported on the Micro-B USB connector:

    $ picocom -b 921600 /dev/ttyUSB0
    root@pumpkin:~#

---
The image is running ssh (dropbear) and avahi in order to make it easily accessible through a Network Interface:

    $ ssh root@pumpkin.local
    root@pumpkin:~#

The image is also configuring a USB Gadget on the USB Type-C connector that can be used as a Network Interface to connect to the board.

## Configuring the Wi-Fi

To configure the board to connect to your Wi-Fi AP you need to run the following command:

    root@pumpkin:~# wpa_passphrase [SSID] [PASSPHRASE] >> /etc/wpa_supplicant.conf

This command needs to be typed only once, the Wi-Fi network will be saved in
the *wpa_supplicant.conf* file. [SSID] needs to be replaced with your
Wi-Fi SSID, and [PASSPHRASE] needs to be replaced with the passphrase
associated to that SSID.
