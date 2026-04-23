package com.bakhmetow.domain.usecase

import com.bakhmetow.domain.util.ServerConnectionManager

internal class DisconnectFromServerUseCase(
    private val connectionManager: ServerConnectionManager
) {
    suspend operator fun invoke() = connectionManager.disconnect()
}