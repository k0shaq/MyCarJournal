package com.sanek.mycarjournal.domain

class InputRecordFuelAverageConsumption(private val carRepository: CarRepository) {

    suspend fun inputRecordFuelAverageConsumption(averageConsumption: Float) {
        carRepository.inputRecordFuelAverageConsumption(averageConsumption)
    }
}