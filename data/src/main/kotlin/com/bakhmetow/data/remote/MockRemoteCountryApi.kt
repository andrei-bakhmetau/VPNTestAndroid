package com.bakhmetow.data.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class MockRemoteCountryApi @Inject constructor(
    @param:ApplicationContext private val appContext: Context,
) : CountryApi {
    override suspend fun getCountries(): List<CountryDto> {
        delay(Random.nextInt(3000) + 2000L)

        val jsonString = appContext.assets.open("mock_country_data_json")
            .bufferedReader()
            .use { it.readText() }

        val listType = object : TypeToken<List<CountryDto>>() {}.type
        return Gson().fromJson<List<CountryDto>>(jsonString, listType)
    }
}