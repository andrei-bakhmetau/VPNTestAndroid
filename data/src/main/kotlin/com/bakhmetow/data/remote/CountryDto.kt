package com.bakhmetow.data.remote

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name") val name: CountryNameDto,
    @SerializedName("flag") val flag: CountryFlagDto
)