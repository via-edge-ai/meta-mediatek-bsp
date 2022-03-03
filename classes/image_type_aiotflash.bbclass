inherit image-artifact-names

IMAGE_CMD_TAR = "tar --xattrs --xattrs-include=*"
CONVERSION_CMD:tar = "touch ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}; ${IMAGE_CMD_TAR} --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.tar -C ${TAR_IMAGE_ROOTFS} . || [ $? -eq 1 ]"
CONVERSIONTYPES:append = " tar"

AIOTFLASH_SYSROOT = "${WORKDIR}/tmp-aiotflash"
do_image_aiotflash[dirs] = "${AIOTFLASH_SYSROOT}"
do_image_aiotflash[cleandirs] = "${AIOTFLASH_SYSROOT}"
TAR_IMAGE_ROOTFS:task-image-aiotflash = "${AIOTFLASH_SYSROOT}"
IMAGE_TYPEDEP:aiotflash = "wic.img"
do_image_aiotflash[depends] += "rity-tools:do_deploy \
                                virtual/bootloader:do_deploy \
                                trusted-firmware-a:do_deploy \
                                virtual/lk:do_deploy \
                                jq-native:do_populate_sysroot"
IMAGE_CMD:aiotflash () {
    tmp_pack_dir=${AIOTFLASH_SYSROOT}/${IMAGE_NAME}
    mkdir -p ${tmp_pack_dir}
    cp -aL ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.manifest ${tmp_pack_dir}
    cp -aL ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.testdata.json ${tmp_pack_dir}
    cp -aL ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.wic.img ${tmp_pack_dir}
    cp -a ${DEPLOY_DIR_IMAGE}/rity.json ${tmp_pack_dir}
    cp -a ${DEPLOY_DIR_IMAGE}/partitions.json ${tmp_pack_dir}
    cp -a ${DEPLOY_DIR_IMAGE}/lk.bin ${tmp_pack_dir}

    for f in `cat ${tmp_pack_dir}/rity.json | jq -r ".partitions[]|strings"`; do
        # When "u-boot-env.bin" is requested, we should pack "u-boot-initial-env"
        if [ ${f} = "u-boot-env.bin" ]; then
            f="u-boot-initial-env"
        fi

        # The requested binary is either in the deploy or the image staging dir
        cp -aL ${DEPLOY_DIR_IMAGE}/${f} ${tmp_pack_dir} || cp -aL ${IMGDEPLOYDIR}/${f} ${tmp_pack_dir}
    done
}
