package com.bakhmetow.data.mapper

import com.bakhmetow.data.local.CountryEntity
import com.bakhmetow.data.remote.CountryDto
import javax.inject.Inject

class CountryDtoToEntityMapper @Inject constructor() {
    operator fun invoke(item: CountryDto): CountryEntity =
        CountryEntity(item.name.common, item.flag.url)
}