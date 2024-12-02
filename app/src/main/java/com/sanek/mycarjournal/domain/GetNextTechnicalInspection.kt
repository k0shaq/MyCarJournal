package com.sanek.mycarjournal.domain

import java.time.LocalDate

class GetNextTechnicalInspection(private val carRepository: CarRepository) {

    fun getNextTechnicalInspection(): String {
        return carRepository.getNextTechnicalInspection()
    }
}