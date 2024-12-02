package com.sanek.mycarjournal.domain

class InputRecordFuelAverageConsumption(private val carRepository: CarRepository) {

    fun inputRecordFuelAverageConsumption(averageConsumption: Float) {
        carRepository.inputRecordFuelAverageConsumption(averageConsumption)
    }
}