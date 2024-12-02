package com.sanek.mycarjournal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FuelDAO {

    @Query("SELECT * FROM fuel ORDER BY id DESC LIMIT 10")
    suspend fun getLastTenRecords(): List<FuelEntity>

    @Query("SELECT consumption FROM fuel ORDER BY id DESC LIMIT 1")
    suspend fun getLastTRecord(): Float


    @Insert
    suspend fun insertFuelRecord(fuelRecord: FuelEntity)
}