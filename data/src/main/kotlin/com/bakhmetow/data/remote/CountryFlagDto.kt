package com.bakhmetow.data.remote

import com.google.gson.annotations.SerializedName

data class CountryFlagDto(
    @SerializedName("svg") val url: String
)