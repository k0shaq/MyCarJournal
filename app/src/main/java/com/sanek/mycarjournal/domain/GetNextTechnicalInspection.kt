package com.sanek.mycarjournal.domain

import java.time.LocalDate

class GetNextTechnicalInspection(private val carRepository: CarRepository) {

    suspend fun getNextTechnicalInspection(): String {
        return carRepository.getNextTechnicalInspection()
    }
}