#!/bin/sh

# Command line parameters
ritydir=$1
machine=$2
imagename=$3

if [ $# -lt 3 ]; then
	echo "Usage: $0 /path/to/rity machine image_name"
	echo
	echo "This script allows you to copy the kernel configuration. This can"
	echo "be used as source config when building a linux kernel outside of"
	echo "yocto."
	echo
	echo "For this script to work, a yocto image should have be built for"
	echo "the targetted machine"
	echo
	echo "Example: $0 ~/rity/ i500-pumpkin core-image-weston"
	exit 1
fi

get_var_from_json() {
	local machinedir="$ritydir/build/tmp/deploy/images/$machine"
	local testdata_file="$machinedir/$imagename-$machine.testdata.json"
	grep "$1" "$testdata_file" | sed -n "s/    \"$1\": \"\(.*\)\",/\1/pg"
}

setup_env() {
	kernel_devicetree=$(get_var_from_json KERNEL_DEVICETREE)
	if [ -z "$kernel_devicetree" ]; then
		echo "Kernel device-tree could not be found. Please make sure $imagename has been fully built once."
		exit 1
	fi
}

generate_kernel_its() {
	local dts="$(echo "$kernel_devicetree" | tr '/' '_')"
	cp "$ritydir/src/meta-mediatek-bsp/docs/kernel-fitimage.its" "$machine.its"
	sed -i "s/KERNEL_DEVICETREE_ESCAPED/$dts/g" "$machine.its"
	sed -i "s@KERNEL_DEVICETREE@$kernel_devicetree@g" "$machine.its"
}

setup_env
generate_kernel_its
