package com.sanek.mycarjournal.domain

class UpdateOilChange(private val carRepository: CarRepository) {

    fun updateOilChange() {
        carRepository.updateOilChange()
    }
}