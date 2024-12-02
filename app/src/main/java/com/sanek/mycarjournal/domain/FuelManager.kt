package com.sanek.mycarjournal

data class FuelManager(
    var currentAverageConsumption: Float = 0.0f,
    val lastTenRecords: List<Float> = listOf()
)
