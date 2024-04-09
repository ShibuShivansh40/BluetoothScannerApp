package com.realworld.bluetoothconnphilipcompose

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class FoundDeviceReceiver(
    private val onDeviceFound : (android.bluetooth.BluetoothDevice) -> Unit): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            //BluetoothDevice is of the package  android.bluetooth.BluetoothDevice
            BluetoothDevice.ACTION_FOUND -> {
                val device = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
                    intent.getParcelableExtra(
                        BluetoothDevice.EXTRA_DEVICE,BluetoothDevice::class.java
                    )
                }
                else{
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                }
                device?.let(onDeviceFound)
            }
        }
    }
}