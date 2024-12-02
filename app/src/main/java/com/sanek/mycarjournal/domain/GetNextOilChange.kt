package com.sanek.mycarjournal.domain

class GetNextOilChange(private val carRepository: CarRepository) {

    suspend fun getNextOilChange(): String {
        return carRepository.getNextOilChange()
    }
}