package com.bakhmetow.domain.usecase

import com.bakhmetow.domain.model.Country
import com.bakhmetow.domain.util.ServerConnectionManager

internal class ConnectToServerUseCase(
    private val connectionManager: ServerConnectionManager
) {
    suspend operator fun invoke(country: Country) = connectionManager.connect(country)
}