package com.sanek.mycarjournal.domain

class UpdateOilChange(private val carRepository: CarRepository) {

    suspend fun updateOilChange() {
        carRepository.updateOilChange()
    }
}