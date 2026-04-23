package com.bakhmetow.domain.util

import com.bakhmetow.domain.model.Country
import com.bakhmetow.domain.model.VpnConnectionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal object ServerConnectionManager {
    private val mutex = Mutex()

    private val _connectionState =
        MutableStateFlow<VpnConnectionState>(VpnConnectionState.Disconnected)
    val connectionState = _connectionState.asStateFlow()

    suspend fun connect(country: Country) {
        println("ServerConnectionManager: _connectionState.value = ${_connectionState.value}")
        mutex.withLock(this) {
            if (_connectionState.value == VpnConnectionState.Connected(country)
                || _connectionState.value == VpnConnectionState.Connecting(country)
            ) return@withLock

            println("ServerConnectionManager:connecting to $country")
            _connectionState.emit(VpnConnectionState.Connecting(country))
            delay(2000L)
            _connectionState.emit(VpnConnectionState.Connected(country))
        }
    }

    suspend fun disconnect() {
        mutex.withLock {
            if (_connectionState.value == VpnConnectionState.Disconnected) return@withLock

            delay(100L)
            _connectionState.emit(VpnConnectionState.Disconnected)
        }
    }
}