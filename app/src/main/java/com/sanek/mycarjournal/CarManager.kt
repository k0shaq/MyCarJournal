package com.sanek.mycarjournal

import java.time.LocalDate

class CarManager {
    var lastTechnicalInspection: LocalDate = LocalDate.of(1970, 1, 1)
    var lastOilChange: Int = 0


    constructor(){
        // підтяг з БД
    }

    fun updateTechnicalInspection(newDate : LocalDate){
        lastTechnicalInspection = newDate
        // update BD
    }

    fun updateOilChange(){
        val miliageManager = MileageManager()
        lastOilChange = miliageManager.getMileage()
        // update BD
    }
}