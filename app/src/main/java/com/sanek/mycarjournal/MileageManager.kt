package com.sanek.mycarjournal

class MileageManager {
    private var mileage: Long = 0

    constructor(){
// дістати з Бази Даних і зберегти
    }

    fun setMileage(newMil: Int) {
        mileage += newMil
    }
}