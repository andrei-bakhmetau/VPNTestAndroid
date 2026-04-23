package com.bakhmetow.domain.model

sealed interface VpnConnectionState {
    data class Connecting(val country: Country) : VpnConnectionState
    data class Connected(val country: Country) : VpnConnectionState
    data object Disconnected : VpnConnectionState
}