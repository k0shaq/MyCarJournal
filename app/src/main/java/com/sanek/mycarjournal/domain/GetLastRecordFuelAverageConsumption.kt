package com.sanek.mycarjournal.domain

class GetLastRecordFuelAverageConsumption(private val carRepository: CarRepository) {

    suspend fun getLastRecordFuelAverageConsumption() : Float{
        return carRepository.getLastRecordFuelAverageConsumption()
    }
}