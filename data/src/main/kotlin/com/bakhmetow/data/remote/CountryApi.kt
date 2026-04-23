package com.bakhmetow.data.remote

import retrofit2.http.GET

interface CountryApi {
    @GET("all?fields=name,flag")
    suspend fun getCountries(): List<CountryDto>
}