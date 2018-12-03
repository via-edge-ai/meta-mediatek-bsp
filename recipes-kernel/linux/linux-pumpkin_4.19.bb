inherit kernel

DEPENDS += "lz4-native"


LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "4.19.0"
LINUX_VERSION_EXTENSION_append = "-pumpkin"
#STAGING_KERNEL_DIR = "${WORKDIR}/git"

S = "${WORKDIR}/git"

KERNEL_EXTRA_ARGS = "dtbs"
BOOTIMAGE = "boot.img"

SRC_URI = "git://git@gitlab.com/baylibre/pumpkin/linux.git;protocol=ssh;branch=pumpkin-v4.19"
SRCREV = "78096ba0e39c55e128714d9ffa0bb913386624be"

SRC_URI_append = " \
	file://defconfig \
"

fitimage_emit_section_dtb() {

	dtb_csum="sha1"

	cat << EOF >> ${1}
                fdt@${2} {
                        description = "Flattened Device Tree blob";
                        data = /incbin/("${3}");
                        type = "flat_dt";
                        arch = "${UBOOT_ARCH}";
                        compression = "none";
                        load = <${DTB_LOADADDRESS}>;
                        hash@1 {
                                algo = "${dtb_csum}";
                        };
                };
EOF
}

uboot_prep_kimage() {
        linux_comp="lz4"

        # uncompressed elf vmlinux
        vmlinux_path="vmlinux"

        if test "${linux_comp}" = "lz4"; then
                linux_suffix=".lz4"
        elif test "${linux_comp}" = "gzip"; then
                linux_suffix=".gz"
        else
                linux_suffix=""
        fi

	${OBJCOPY} -O binary -R .note -R .comment -S "${vmlinux_path}" linux.bin.raw

        if test "${linux_comp}" = "lz4"; then
                lz4 -l -c1 linux.bin.raw > linux.bin${linux_suffix}
                # append uncompressed filesize info
                dec_size=0
                fsize=$(stat -c "%s" "linux.bin.raw")
                dec_size=$(expr $dec_size + $fsize)
                printf "%08x\n" $dec_size |
                        sed 's/\(..\)/\1 /g' | {
                                read ch0 ch1 ch2 ch3;
                                for ch in $ch3 $ch2 $ch1 $ch0; do
                                        printf `printf '%s%03o' '\\' 0x$ch` >> linux.bin${linux_suffix};
                                done;
                        }
        elif test "${linux_comp}" = "gzip"; then
                gzip -9 linux.bin.raw -c > linux.bin${linux_suffix}
        else
                echo "For none case or another compressing"
        fi

        if ! test "${linux_comp}" = "none"; then
                mv -f "linux.bin${linux_suffix}" linux.bin
        else
                echo "No kerenl compression"
        fi

        echo "${linux_comp}"
}



COMPATIBLE_MACHINE_pumpkin = "pumpkin"
