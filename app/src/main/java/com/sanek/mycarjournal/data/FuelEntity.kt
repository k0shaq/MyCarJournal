package com.sanek.mycarjournal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fuel")
data class FuelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val consumption: Float
)
