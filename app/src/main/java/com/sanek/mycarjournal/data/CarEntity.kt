package com.sanek.mycarjournal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mileage: Int,
    val lastTechnicalInspectionDate: String,
    val lastTechnicalInspectionMill: Int,
    val lastOilChangeDate: String,
    val lastOilChangeMill: Int

)
