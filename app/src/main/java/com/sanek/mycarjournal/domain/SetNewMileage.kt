package com.sanek.mycarjournal.domain

class SetNewMileage(private val carRepository: CarRepository) {

    suspend fun setNewMileage(newMileage: Int) {
        carRepository.setNewMileage(newMileage)
    }
}