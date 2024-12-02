package com.sanek.mycarjournal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FuelDAO {

    @Query("SELECT AVG(consumption) FROM fuel")
    suspend fun getAverageConsumption(): Float

    @Query("SELECT consumption FROM fuel ORDER BY id DESC LIMIT 1")
    suspend fun getLastRecord(): Float


    @Insert
    suspend fun insertFuelRecord(fuelRecord: FuelEntity)
}