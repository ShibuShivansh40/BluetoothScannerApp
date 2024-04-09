package com.realworld.bluetoothcommphilipcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.realworld.bluetoothconnphilipcompose.BluetoothController
import com.realworld.bluetoothconnphilipcompose.presentation.BluetoothUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val  bluetoothController: BluetoothController
) : ViewModel(){
    private val _state = MutableStateFlow(BluetoothUiState())
    val state = combine(
        bluetoothController.scannedDevices,
        bluetoothController.pairedDevices,
        _state
    ){
        scannedDevices, pairedDevices, state ->
        state.copy(
            scannedDevices = scannedDevices,
            pairedDevices = pairedDevices
        )
    }.stateIn(
        scope = viewModelScope, // To use this add ViewModelScope, use androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0 or higher to dependencies
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _state.value
    )

    fun startScan(){
        bluetoothController.startDiscovery()
    }

    fun stopScan(){
        bluetoothController.stopDiscovery()
    }
}