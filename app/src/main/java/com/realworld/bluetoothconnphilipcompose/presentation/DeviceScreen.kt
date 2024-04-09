package com.realworld.bluetoothconnphilipcompose.presentation

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer : () -> Unit,
    onDeviceClick : (com.realworld.bluetoothconnphilipcompose.BluetoothDevice) -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        BluetoothDeviceList(
            pairedDevices = state.pairedDevices, scannedDevices = state.scannedDevices, onClick = onDeviceClick,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(onClick = onStartScan){
                Text(text = "Start Scan")
            }
            Button(onClick = onStopScan){
                Text(text = "Stop Scan")
            }
            Button(onClick = onStartServer){
                Text(text = "Start Server")
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun BluetoothDeviceList(
    pairedDevices: List<com.realworld.bluetoothconnphilipcompose.BluetoothDevice>,
    scannedDevices: List<com.realworld.bluetoothconnphilipcompose.BluetoothDevice>,
    onClick: (com.realworld.bluetoothconnphilipcompose.BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn (modifier = Modifier){
        item{
            Text(
                text = "PAIRED DEVICES",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(pairedDevices){device ->
            Text(
                text = device.name?: "(No Name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp)
            )
        }
        item{
            Text(
                text = "SCANNED DEVICES",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(scannedDevices){device ->
            Text(
                text = device.name?: "(No Name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp)
            )
        }

    }
}