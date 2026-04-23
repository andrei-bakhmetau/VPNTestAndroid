package com.bakhmetow.domain.usecase

import com.bakhmetow.domain.model.Country
import com.bakhmetow.domain.model.Resource
import com.bakhmetow.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class GetCountryListUseCase(private val countryRepository: CountryRepository) {
    operator fun invoke(): Flow<Resource<List<Country>>> = countryRepository.getCountries()
}