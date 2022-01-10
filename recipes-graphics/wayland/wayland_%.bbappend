
do_install:append () {
	if [ ${PREFERRED_PROVIDER_virtual/mesa} = "libmali" ]; then
	  bbplain "remove provided libwayland-egl library in favour of the version in libmali"
	  rm -f ${D}${libdir}/libwayland-egl*
	  rm -f ${D}${libdir}/pkgconfig/wayland-egl.pc
	else
	  bbplain "use provided libwayland-egl library"
	fi
}
