# ToDo

# Development
### Debug over WiFi
0. Connect device using wire in debug mode
0. Press CTRL twice and type `adb tcpip 5555`
0. Find device ip address in its wifi settings or using `adb shell ifconifg` (`adb shell netcfg` on old devices)
0. Disconnect now
0. Press CTRL twice and type `adb connect <device ip>:5555`
