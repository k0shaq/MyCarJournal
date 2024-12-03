package com.sanek.mycarjournal.domain

class GetAverageRecordFuelAverageConsumption(private val carRepository: CarRepository) {

    suspend fun getAverageRecordFuelAverageConsumption(): Float {
        return carRepository.getAverageRecordFuelAverageConsumption()
    }
}