package com.sanek.mycarjournal.domain

class UpdateTechnicalInspection(private val carRepository: CarRepository) {

    fun updateTechnicalInspection() {
        carRepository.updateTechnicalInspection()
    }
}