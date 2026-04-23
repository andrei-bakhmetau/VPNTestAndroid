package com.bakhmetow.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @ColumnInfo(name = "name") @PrimaryKey val name: String,
    @ColumnInfo(name = "flag_url") val flagUrl: String
)