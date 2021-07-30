PROVIDES = "virtual/lk"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

do_deploy () {
	install ${BUILD}/lk.bin ${DEPLOYDIR}/lk.bin
}
addtask do_deploy after do_install
