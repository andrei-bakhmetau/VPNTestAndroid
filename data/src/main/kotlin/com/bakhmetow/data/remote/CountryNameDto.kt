package com.bakhmetow.data.remote

import com.google.gson.annotations.SerializedName

data class CountryNameDto(
    @SerializedName("common") val common: String,
    @SerializedName("official") val official: String,
)