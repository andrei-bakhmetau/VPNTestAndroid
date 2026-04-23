package com.bakhmetow.domain.interactor

import com.bakhmetow.domain.model.Country
import com.bakhmetow.domain.model.VpnConnectionState
import com.bakhmetow.domain.usecase.ConnectToServerUseCase
import com.bakhmetow.domain.usecase.DisconnectFromServerUseCase
import com.bakhmetow.domain.util.ServerConnectionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VpnConnectionInteractor {
    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry = _selectedCountry.asStateFlow()
    private val connectUseCase = ConnectToServerUseCase(ServerConnectionManager)
    private val disconnectUseCase = DisconnectFromServerUseCase(ServerConnectionManager)
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    fun getConnectionState(): Flow<VpnConnectionState> = ServerConnectionManager.connectionState

    fun selectCountry(country: Country?) {
        println("VpnConnectionInteractor: requesting selectCountry = $country")
        if (ServerConnectionManager.connectionState.value == VpnConnectionState.Disconnected) {
            coroutineScope.launch {
                println("VpnConnectionInteractor: selectCountry = $country")
                _selectedCountry.emit(country)
            }
        }
    }

    fun connect() = coroutineScope.launch {
        println("VpnConnectionInteractor: selectedCountry = ${selectedCountry.value}")
        selectedCountry.value?.run { connectUseCase(this) }
    }

    fun disconnect() = coroutineScope.launch { disconnectUseCase() }
}