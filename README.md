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
    $ repo init -u git@gitlab.com:baylibre/rich-iot/manifest.git -b gatesgarth
    $ repo sync
    $ export TEMPLATECONF=${PWD}/src/meta-mediatek-bsp/conf/
    $ source src/poky/oe-init-build-env
    $ bitbake core-image-weston

## Flashing

### Prerequisites

In order for your host machine to be able to talk to the board through USB
without needing root privileges, you need to create a udev rules that will
grant the *plugdev* group access to your device:

    $ echo 'SUBSYSTEM=="usb", ATTR{idVendor}=="0e8d", ATTR{idProduct}=="201c", MODE="0660", GROUP="plugdev"' | sudo tee -a /etc/udev/rules.d/51-android.rules
    $ sudo udevadm control --reload-rules
    $ sudo udevadm trigger

If your user is not already member of the *plugdev* group:

	$ sudo usermod -a -G plugdev $USER

This last command requires you to log out and log back in to your account to be
in effect.

### Flashing everything

    $ cd rich-iot/build/tmp/deploy/images/i300b-pumpkin
    $ ./flashimage.py -i core-image-weston
                                     Checking image
    --------------------------------------------------------------------------------
                                  MBR_EMMC : PASS
                                   bl2.img : PASS
                            u-boot-env.bin : PASS
                                   fip.bin : PASS
                                  fitImage : PASS
      core-image-weston-i300b-pumpkin.ext4 : PASS

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
- *rootfs*: for flashing the root filesystem (core-image-weston-i300b-pumpkin.ext4).

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
    $ fastboot flash rootfs core-image-weston-i300b-pumpkin.ext4
    $ fastboot continue

## local.conf options

Options available in `build/conf/local.conf` to modify the BSP:

* `MACHINE`: This option defines the machine configuration used to generates
	the image. It is set by default to `i300b-pumpkin` but can be changed to
	any of the machine available.

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
