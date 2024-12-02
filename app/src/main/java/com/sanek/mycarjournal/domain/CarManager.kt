package com.sanek.mycarjournal.domain

import java.time.LocalDate

data class CarManager(
    var lastTechnicalInspectionDate: LocalDate = LocalDate.of(1970, 1, 1),
    var lastTechnicalInspectionMill: Int = 0,
    var lastOilChangeDate: LocalDate = LocalDate.of(1970, 1, 1),
    var lastOilChangeMill: Int = 0
)



