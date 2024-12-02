package com.sanek.mycarjournal.domain

import java.time.LocalDate

interface CarRepository {
    suspend fun updateOilChange()
    suspend fun updateTechnicalInspection()
    suspend fun inputRecordFuelAverageConsumption(averageConsumption: Float)
    suspend fun setNewMileage(newMileage: Int)
    suspend fun getMileage(): Int
    suspend fun getNextTechnicalInspection(): String
    suspend fun getNextOilChange(): String
    suspend fun getLastRecordFuelAverageConsumption() : Float
    suspend fun getAverageRecordFuelAverageConsumption() : Float
}