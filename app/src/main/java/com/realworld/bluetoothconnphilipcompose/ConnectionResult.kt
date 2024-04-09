package com.realworld.bluetoothconnphilipcompose

sealed interface ConnectionResult {
    object ConnectionEstablished : ConnectionResult
    data class Error(val message: String): ConnectionResult

}