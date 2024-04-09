package com.realworld.bluetoothconnphilipcompose.presentation

import com.realworld.bluetoothconnphilipcompose.BluetoothDevice

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),

)
