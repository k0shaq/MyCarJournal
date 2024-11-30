package com.sanek.mycarjournal

class MileageManager {
    private var mileage: Int = 0
        get() = field

    constructor(){
// дістати з Бази Даних і зберегти
    }

    fun setMileage(newMil: Int) {
        mileage += newMil
        // update BD
    }

    fun getMileage(): Int = mileage
}