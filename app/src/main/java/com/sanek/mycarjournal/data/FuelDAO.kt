package com.sanek.mycarjournal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FuelDAO {

    @Query("SELECT * FROM fuel ORDER BY id DESC LIMIT 10")
    fun getLastTenRecords(): List<FuelEntity>

    @Query("SELECT consumption FROM fuel ORDER BY id DESC LIMIT 1")
    fun getLastTRecord(): Float


    @Insert
    fun insertFuelRecord(fuelRecord: FuelEntity)
}