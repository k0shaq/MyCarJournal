package com.sanek.mycarjournal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDAO {

    @Query("SELECT mileage FROM car LIMIT 1")
    suspend fun getMileage(): Int

    @Query("SELECT lastTechnicalInspectionDate FROM car LIMIT 1")
    suspend fun getLastTechnicalInspectionDate(): String

    @Query("SELECT lastTechnicalInspectionMill FROM car LIMIT 1")
    suspend fun getLastTechnicalInspectionMill(): Int

    @Query("SELECT lastOilChangeDate FROM car LIMIT 1")
    suspend fun getLastOilChangeDate(): String

    @Query("SELECT lastOilChangeMill FROM car LIMIT 1")
    suspend fun getLastOilChangeMill(): Int

    @Query("UPDATE car SET mileage = :newMileage")
    suspend fun updateMileage(newMileage: Int)

    @Query("UPDATE car SET lastTechnicalInspectionDate = :newTechInspectDate")
    suspend fun updateTechnicalInspectionDate(newTechInspectDate: String)

    @Query("UPDATE car SET lastTechnicalInspectionMill = :newTechInspectMill")
    suspend fun updateTechnicalInspectionMill(newTechInspectMill: Int)

    @Query("UPDATE car SET lastOilChangeDate = :newOilChangeDate")
    suspend fun updateOilChangeDate(newOilChangeDate: String)

    @Query("UPDATE car SET lastOilChangeMill = :newOilChangeMill")
    suspend fun updateOilChangeMill(newOilChangeMill: Int)

    @Query("SELECT Count(id) from car")
    suspend fun getCountElements(): Int

    @Insert
    suspend fun insert(ce: CarEntity)

}

