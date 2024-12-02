package com.sanek.mycarjournal.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CarEntity::class, FuelEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun carDao(): CarDAO
    abstract fun fuelDao(): FuelDAO
}