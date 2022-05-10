SIMPLECLIENTS = "${@bb.utils.contains("PREFERRED_PROVIDER_virtual/mesa", "imgtec-pvr", "damage,im,egl,shm,touch,dmabuf-v4l,dmabuf-egl", "all", d)}"
