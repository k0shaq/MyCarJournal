package com.sanek.mycarjournal.domain

class GetMileage(private val carRepository: CarRepository) {

    suspend fun getMileage(): Int {
        return carRepository.getMileage()
    }
}