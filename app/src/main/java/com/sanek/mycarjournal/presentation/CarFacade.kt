package com.sanek.mycarjournal.presentation

import com.sanek.mycarjournal.domain.GetAverageRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.GetLastRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.GetMileage
import com.sanek.mycarjournal.domain.GetNextOilChange
import com.sanek.mycarjournal.domain.GetNextTechnicalInspection
import com.sanek.mycarjournal.domain.InputRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.SetNewMileage
import com.sanek.mycarjournal.domain.UpdateOilChange
import com.sanek.mycarjournal.domain.UpdateTechnicalInspection

class CarFacade(
    private val getAverageRecordFuelAverageConsumptionUseCase: GetAverageRecordFuelAverageConsumption,
    private val getLastRecordFuelAverageConsumptionUseCase: GetLastRecordFuelAverageConsumption,
    private val getMileageUseCase: GetMileage,
    private val getNextOilChangeUseCase: GetNextOilChange,
    private val getNextTechnicalInspectionUseCase: GetNextTechnicalInspection,
    private val inputRecordFuelAverageConsumptionUseCase: InputRecordFuelAverageConsumption,
    private val setNewMileageUseCase: SetNewMileage,
    private val updateOilChangeUseCase: UpdateOilChange,
    private val updateTechnicalInspectionUseCase: UpdateTechnicalInspection

) {
    suspend fun getAverageRecordFuelAverageConsumption(): Float {
        return getAverageRecordFuelAverageConsumptionUseCase.getAverageRecordFuelAverageConsumption()
    }

    suspend fun getLastRecordFuelAverageConsumption(): Float {
        return getLastRecordFuelAverageConsumptionUseCase.getLastRecordFuelAverageConsumption()
    }

    suspend fun getMileage(): Int {
        return getMileageUseCase.getMileage()
    }

    suspend fun getNextOilChange(): String {
        return getNextOilChangeUseCase.getNextOilChange()
    }

    suspend fun getNextTechnicalInspection(): String {
        return getNextTechnicalInspectionUseCase.getNextTechnicalInspection()
    }

    suspend fun inputRecordFuelAverageConsumption(averageConsumption: Float) {
        inputRecordFuelAverageConsumptionUseCase.inputRecordFuelAverageConsumption(
            averageConsumption
        )
    }

    suspend fun setNewMileage(newMileage: Int) {
        setNewMileageUseCase.setNewMileage(newMileage)
    }

    suspend fun updateOilChange() {
        updateOilChangeUseCase.updateOilChange()
    }

    suspend fun updateTechnicalInspection() {
        updateTechnicalInspectionUseCase.updateTechnicalInspection()
    }


}
