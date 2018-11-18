#! /bin/sh
### BEGIN INIT INFO
# Provides:          usbgadget-net
# Required-Start:
# Required-Stop:
# Default-Start:     2 3 4 5
# Default-Stop:      2 3 4 5
### END INIT INFO

# Configure gadget
cd /sys/kernel/config/usb_gadget
mkdir net1
cd net1
echo "0x1d6b" > idVendor
echo "0x0104" > idProduct
mkdir strings/0x409
echo "0123456789" > strings/0x409/serialnumber
echo "BayLibre / Gossamer" > strings/0x409/manufacturer
echo "Pumpkin Board" > strings/0x409/product
mkdir functions/ecm.usb0
mkdir configs/c.1
mkdir configs/c.1/strings/0x409
ln -s functions/ecm.usb0 configs/c.1
echo musb-hdrc.0.auto > UDC

# Configure interface
ifconfig usb0 up
ifconfig usb0 192.168.0.1
udhcpd /etc/udhcpd.conf
