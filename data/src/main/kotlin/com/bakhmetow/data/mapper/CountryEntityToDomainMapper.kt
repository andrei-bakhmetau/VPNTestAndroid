package com.bakhmetow.data.mapper

import com.bakhmetow.data.local.CountryEntity
import com.bakhmetow.domain.model.Country
import javax.inject.Inject

class CountryEntityToDomainMapper @Inject constructor() {
    operator fun invoke(items: List<CountryEntity>): List<Country> =
        items.map { countryEntity ->
            Country(name = countryEntity.name, countryEntity.flagUrl)
        }
}