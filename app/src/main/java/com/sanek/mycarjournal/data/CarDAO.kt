package com.sanek.mycarjournal.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDAO {

    @Query("SELECT mileage FROM car LIMIT 1")
    fun getMileage(): Int

    @Query("SELECT lastTechnicalInspectionDate FROM car LIMIT 1")
    fun getLastTechnicalInspectionDate(): String

    @Query("SELECT lastTechnicalInspectionMill FROM car LIMIT 1")
    fun getLastTechnicalInspectionMill(): Int

    @Query("SELECT lastOilChangeDate FROM car LIMIT 1")
    fun getLastOilChangeDate(): String

    @Query("SELECT lastOilChangeMill FROM car LIMIT 1")
    fun getLastOilChangeMill(): Int

    @Query("UPDATE car SET mileage = :newMileage WHERE id = 0")
    fun updateMileage(newMileage: Int)

    @Query("UPDATE car SET lastTechnicalInspectionDate = :newTechInspectDate WHERE id = 0")
    fun updateTechnicalInspectionDate(newTechInspectDate: String)

    @Query("UPDATE car SET lastTechnicalInspectionMill = :newTechInspectMill WHERE id = 0")
    fun updateTechnicalInspectionMill(newTechInspectMill: Int)

    @Query("UPDATE car SET lastOilChangeDate = :newOilChangeDate WHERE id = 0")
    fun updateOilChangeDate(newOilChangeDate: String)

    @Query("UPDATE car SET lastOilChangeMill = :newOilChangeMill WHERE id = 0")
    fun updateOilChangeMill(newOilChangeMill: Int)

    @Update
    fun insertCar(car: CarEntity)
}

