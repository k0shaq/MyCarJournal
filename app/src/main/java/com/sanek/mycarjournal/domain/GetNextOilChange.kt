package com.sanek.mycarjournal.domain

class GetNextOilChange(private val carRepository: CarRepository) {

    fun getNextOilChange(): Int {
        return carRepository.getNextOilChange()
    }
}