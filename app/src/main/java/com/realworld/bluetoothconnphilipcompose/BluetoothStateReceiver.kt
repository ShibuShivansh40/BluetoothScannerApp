package com.realworld.bluetoothconnphilipcompose

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class BluetoothStateReceiver(
    private val onStateChanged : (isConnected : Boolean,android.bluetooth.BluetoothDevice) -> Unit): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val device = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            intent?.getParcelableExtra(
                BluetoothDevice.EXTRA_DEVICE,BluetoothDevice::class.java
            )
        }
        else{
            intent?.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
        }
        when(intent?.action){
            //BluetoothDevice is of the package  android.bluetooth.BluetoothDevice
            BluetoothDevice.ACTION_ACL_CONNECTED -> {
                onStateChanged(true, device?: return)
            }
            BluetoothDevice.ACTION_ACL_DISCONNECTED ->{
                onStateChanged(false, device?: return)
            }
        }
    }
}