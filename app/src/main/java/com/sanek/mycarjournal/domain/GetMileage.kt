package com.sanek.mycarjournal.domain

class GetMileage(private val carRepository: CarRepository) {

    fun getMileage(): Int {
        return carRepository.getMileage()
    }
}