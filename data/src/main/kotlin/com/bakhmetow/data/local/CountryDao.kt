package com.bakhmetow.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    fun getAll(): Flow<List<CountryEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM countries)")
    suspend fun hasData(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg countries: CountryEntity)

    @Update
    suspend fun update(vararg countries: CountryEntity)

    @Delete
    suspend fun delete(vararg countries: CountryEntity)
}