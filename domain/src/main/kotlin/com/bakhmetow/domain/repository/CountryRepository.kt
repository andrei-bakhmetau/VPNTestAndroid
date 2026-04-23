package com.bakhmetow.domain.repository

import com.bakhmetow.domain.model.Country
import com.bakhmetow.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountries(): Flow<Resource<List<Country>>>

    suspend fun refreshCountries(): Resource<Unit>
}