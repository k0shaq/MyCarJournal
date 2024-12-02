package com.sanek.mycarjournal.domain

import java.time.LocalDate

interface CarRepository {
    fun updateOilChange()
    fun updateTechnicalInspection()
    fun inputRecordFuelAverageConsumption(averageConsumption: Float)
    fun setNewMileage(newMileage: Int)
    fun getMileage(): Int
    fun getNextTechnicalInspection(): LocalDate
    fun getNextOilChange(): Int
}