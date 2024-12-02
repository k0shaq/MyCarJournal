package com.sanek.mycarjournal.domain

class UpdateTechnicalInspection(private val carRepository: CarRepository) {

    suspend fun updateTechnicalInspection() {
        carRepository.updateTechnicalInspection()
    }
}