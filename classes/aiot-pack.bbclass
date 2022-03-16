# Provide a file convinient to download and suitable for aiot-flash

inherit image-artifact-names

do_aiotpack[depends] += "jq-native:do_populate_sysroot"
do_aiotpack () {
	if ! [ -f ${DEPLOY_DIR_IMAGE}/rity.json ]; then
		# likely not an mtk based platform - exit gracefully
		return 0
	fi

	rm -f ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}*aiot-flash.tar
	tmp_pack_root=${DEPLOY_DIR_IMAGE}/.aiot-flash-${IMAGE_NAME}
	tmp_pack_dir=${tmp_pack_root}/${IMAGE_NAME}
	mkdir -p ${tmp_pack_dir}
	cp -aL ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.manifest ${tmp_pack_dir}
	cp -aL ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.testdata.json ${tmp_pack_dir}
	cp -aL ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.wic.img ${tmp_pack_dir}
	cp -a ${DEPLOY_DIR_IMAGE}/rity.json ${tmp_pack_dir}
	cp -a ${DEPLOY_DIR_IMAGE}/partitions.json ${tmp_pack_dir}
	cp -a ${DEPLOY_DIR_IMAGE}/lk.bin ${tmp_pack_dir}

	for f in `cat ${tmp_pack_dir}/rity.json | jq -r ".partitions[]|strings"`; do
		# When "u-boot-env.bin" is requested, we should pack "u-boot-initial-env"
		if [ ${f} = "u-boot-env.bin" ]; then
			f="u-boot-initial-env"
		fi

		cp -aL ${DEPLOY_DIR_IMAGE}/${f} ${tmp_pack_dir}
	done

	tar cf ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.aiot-flash.tar -C ${tmp_pack_root} .
	ln -sf ${IMAGE_NAME}.aiot-flash.tar ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.aiot-flash.tar
	rm -rf ${tmp_pack_root}
}
addtask do_aiotpack after do_image_complete before do_build
