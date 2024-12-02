package com.sanek.mycarjournal.data

import com.sanek.mycarjournal.domain.CarRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class CarRepositoryImpl(private val carDao: CarDAO, private val fuelDao: FuelDAO) : CarRepository {
    override suspend fun updateOilChange() {
        val mileageNow = carDao.getMileage()
        val todayIs = LocalDate.now()
        carDao.updateOilChangeDate(todayIs.toString())
        carDao.updateOilChangeMill(mileageNow)
    }

    override suspend fun updateTechnicalInspection() {
        val mileageNow = carDao.getMileage()
        val todayIs = LocalDate.now()
        carDao.updateTechnicalInspectionDate(todayIs.toString())
        carDao.updateTechnicalInspectionMill(mileageNow)
    }

    override suspend fun inputRecordFuelAverageConsumption(averageConsumption: Float) {
        fuelDao.insertFuelRecord(FuelEntity(consumption = averageConsumption))
    }

    override suspend fun setNewMileage(newMileage: Int) {
        carDao.updateMileage(newMileage)
    }

    override suspend fun getMileage(): Int {
        return carDao.getMileage()
    }

    override suspend fun getNextTechnicalInspection(): String {
        val mileageNow = carDao.getMileage()
        val dateNow = LocalDate.now()

        val lastTIDateString = carDao.getLastTechnicalInspectionDate()
        val lastTIMill = carDao.getLastTechnicalInspectionMill()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val lastTIDate = LocalDate.parse(lastTIDateString, formatter)

        val timeTI = lastTIDate.plusYears(1)
        val timeToTI = ChronoUnit.DAYS.between(dateNow, timeTI)
        val millToTI = (lastTIMill + 15000) - mileageNow

        return if (timeToTI <= 0 || millToTI <= 0) "Необхідно провести Т/О."
        else "До Т/О залишилось $millToTI км або $timeToTI днів."
    }

    override suspend fun getNextOilChange(): String {
        val mileageNow = carDao.getMileage()
        val dateNow = LocalDate.now()

        val lastOCDateString = carDao.getLastOilChangeDate()
        val lastOCMill = carDao.getLastOilChangeMill()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val lastOCDate = LocalDate.parse(lastOCDateString, formatter)

        val timeTI = lastOCDate.plusYears(1)
        val timeToOC = ChronoUnit.DAYS.between(dateNow, timeTI)
        val millToOC = (lastOCMill + 8000) - mileageNow

        return if (timeToOC <= 0 || millToOC <= 0) "Необхідно замінити масло."
        else "До заміни масла залишилось $millToOC км або $timeToOC днів."
    }

    override suspend fun getLastRecordFuelAverageConsumption(): Float {
        return fuelDao.getLastRecord()
    }

    override suspend fun getAverageRecordFuelAverageConsumption(): Float {
        return fuelDao.getAverageConsumption()
    }
}