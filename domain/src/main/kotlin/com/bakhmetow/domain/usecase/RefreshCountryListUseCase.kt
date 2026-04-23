package com.bakhmetow.domain.usecase

import com.bakhmetow.domain.model.Resource
import com.bakhmetow.domain.repository.CountryRepository

class RefreshCountryListUseCase(private val countryRepository: CountryRepository) {
    suspend operator fun invoke(): Resource<Unit> = countryRepository.refreshCountries()
}