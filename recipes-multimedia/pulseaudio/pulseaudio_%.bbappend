FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

VESPER_PATCH = "${@bb.utils.contains('MACHINE_FEATURES', 'vesper-hat', 'file://0001-Setting-PulseAudio-default-number-of-channels-to-8-w.patch', '', d)}"

SRC_URI_append = " \
	file://0001-pumpkin-add-static-alsa-driver-for-audio-sink.patch \
	${VESPER_PATCH} \
"
