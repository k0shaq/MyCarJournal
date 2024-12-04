package com.sanek.mycarjournal.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sanek.mycarjournal.R
import com.sanek.mycarjournal.data.CarEntity
import com.sanek.mycarjournal.data.DatabaseProvider
import androidx.lifecycle.lifecycleScope
import com.sanek.mycarjournal.logger.FileLogger
import com.sanek.mycarjournal.data.CarRepositoryImpl
import com.sanek.mycarjournal.data.FuelEntity
import com.sanek.mycarjournal.domain.GetAverageRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.GetLastRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.GetMileage
import com.sanek.mycarjournal.domain.GetNextOilChange
import com.sanek.mycarjournal.domain.GetNextTechnicalInspection
import com.sanek.mycarjournal.domain.InputRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.SetNewMileage
import com.sanek.mycarjournal.domain.UpdateOilChange
import com.sanek.mycarjournal.domain.UpdateTechnicalInspection
import com.sanek.mycarjournal.logger.HistoryActivity
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {
    private lateinit var carFacade: CarFacade
    private lateinit var mileageTextView: TextView
    private lateinit var techInspectTextView: TextView
    private lateinit var oilChangeTextView: TextView
    private lateinit var consumptionTextView: TextView
    var newMileage: Int = -1
    var newConsumption: Float = -1.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        FileLogger.init(applicationContext)
        val db = DatabaseProvider.getDatabase(this)
        val carDao = db.carDao()
        val fuelDao = db.fuelDao()

        lifecycleScope.launch {
            val countCars = carDao.getCountElements()
            val countFuel = fuelDao.getCountElements()

            if (countCars == 0){
                val date = LocalDate.of(1970,1,1)
                val carEntity = CarEntity(mileage = 0, lastTechnicalInspectionMill = 0, lastOilChangeMill = 0, lastOilChangeDate = date.toString(), lastTechnicalInspectionDate = date.toString())
                carDao.insert(carEntity)
            }

            if (countFuel == 0){
                fuelDao.insertFuelRecord(FuelEntity(consumption = 5.0f))
            }
        }

        val carRepository = CarRepositoryImpl(carDao, fuelDao)
        carFacade = CarFacade(
            GetAverageRecordFuelAverageConsumption(carRepository),
            GetLastRecordFuelAverageConsumption(carRepository),
            GetMileage(carRepository),
            GetNextOilChange(carRepository),
            GetNextTechnicalInspection(carRepository),
            InputRecordFuelAverageConsumption(carRepository),
            SetNewMileage(carRepository),
            UpdateOilChange(carRepository),
            UpdateTechnicalInspection(carRepository)
        )

        mileageTextView = findViewById(R.id.mileageTextViewValue)
        techInspectTextView = findViewById(R.id.timeToTIDateTextViewValue)
        oilChangeTextView = findViewById(R.id.oilChangeTimeTextViewValue)
        consumptionTextView = findViewById(R.id.fuelConsumptionTextViewValue)

        val btnTI = findViewById<Button>(R.id.tiCompletedButton)
        val btnOC = findViewById<Button>(R.id.oilChangeCompletedButton)
        val btnConsumption = findViewById<Button>(R.id.changeFuelConsumptionButton)
        val btnMileage = findViewById<Button>(R.id.changeMileageButton)
        val btnHistory = findViewById<Button>(R.id.historyButton)

        btnHistory.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        btnTI.setOnClickListener{
            updateTI()
        }

        btnOC.setOnClickListener{
            updateOC()
        }

        btnConsumption.setOnClickListener{
            enterRecordConsumption()
        }

        btnMileage.setOnClickListener{
            enterMileage()
        }

        lifecycleScope.launch {
            val mill = carFacade.getMileage()
            mileageTextView.text = String.format("%,d", mill) + " км"

            val dateTI = carFacade.getNextTechnicalInspection()
            techInspectTextView.text = dateTI

            val dateOC = carFacade.getNextOilChange()
            oilChangeTextView.text = dateOC

            val lastRecord = carFacade.getLastRecordFuelAverageConsumption()
            val averageConsumption = carFacade.getAverageRecordFuelAverageConsumption()
            if (lastRecord > averageConsumption) consumptionTextView.text =
                "Розхід становить ${String.format("%.1f", lastRecord)} л / 100 км, що на ${String.format("%.1f", lastRecord - averageConsumption)} л більше середнього"
            else if (lastRecord < averageConsumption) consumptionTextView.text =
                "Розхід становить ${String.format("%.1f", lastRecord)} л / 100 км, що на ${String.format("%.1f",  averageConsumption - lastRecord)} л менше середнього"
            else consumptionTextView.text =
                "Розхід становить ${String.format("%.1f", lastRecord)} л / 100 км, що є рівним середньому значенню"
        }

    }

    private fun updateTI(){
        lifecycleScope.launch {
            carFacade.updateTechnicalInspection()
            var dateTI = carFacade.getNextTechnicalInspection()
            techInspectTextView.text = dateTI
        }
        Toast.makeText(this, "Оновлено проведення технічного огляду", Toast.LENGTH_SHORT).show()
        createLog("Проведено технічний огляд")
    }

    private fun updateOC(){
        lifecycleScope.launch {
            carFacade.updateOilChange()
            var dateOC = carFacade.getNextOilChange()
            oilChangeTextView.text = dateOC
        }
        Toast.makeText(this, "Оновлено заміну масла", Toast.LENGTH_SHORT).show()
        createLog("Замінено масло")
    }

    fun updateMileage(){
        if (newMileage != -1){
            lifecycleScope.launch {
                var mileageNow = carFacade.getMileage()
                if (mileageNow > newMileage) { Toast.makeText(this@MainActivity, "Новий пробіг має бути більшим за попередній", Toast.LENGTH_SHORT).show() }
                else { carFacade.setNewMileage(newMileage)
                mileageTextView.text = String.format("%,d", newMileage) + " км"
                createLog("Оновлено пробіг: ${String.format("%,d", newMileage)} км")
                newMileage = -1
                val dateTI = carFacade.getNextTechnicalInspection()
                val dateOC = carFacade.getNextOilChange()
                techInspectTextView.text = dateTI
                oilChangeTextView.text = dateOC
                }
            }
        }
    }

    fun updateConsumtion(){
        if (newConsumption != -1.0f){
            lifecycleScope.launch {
                carFacade.inputRecordFuelAverageConsumption(newConsumption)
                var averageCons = carFacade.getAverageRecordFuelAverageConsumption()
                if (newConsumption > averageCons) consumptionTextView.text =
                    "Розхід становить ${String.format("%.1f", newConsumption)} л / 100 км, що на ${String.format("%.1f", newConsumption - averageCons)} л більше середнього"
                else if (newConsumption < averageCons) consumptionTextView.text =
                    "Розхід становить ${String.format("%.1f", newConsumption)} л / 100 км, що на ${String.format("%.1f",  averageCons - newConsumption)} л менше середнього"
                else consumptionTextView.text =
                    "Розхід становить ${String.format("%.1f", newConsumption)} л / 100 км, що є рівним середньому значенню"
                createLog("Оновлено розхід палива: ${String.format("%.1f", newConsumption)} л/100 км")
                newConsumption = -1.0f
            }
        }
    }

    private fun enterMileage() {
        val input = EditText(this)
        input.hint = "Введіть новий пробіг"

        AlertDialog.Builder(this)
            .setTitle("Оновлення пробігу")
            .setMessage("Будь ласка, введіть пробіг автомобіля:")
            .setView(input)
            .setPositiveButton("Зберегти") { dialog, _ ->
                val enteredData = input.text.toString()
                handleInputDataMileage(enteredData)
                dialog.dismiss()
            }
            .setNegativeButton("Скасувати") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun handleInputDataMileage(data: String) {
        val mileage = data.toIntOrNull()
        if (mileage != null && mileage > 0) {
            newMileage = mileage
            updateMileage()
        } else {
            Toast.makeText(this, "Введіть коректне число", Toast.LENGTH_SHORT).show()
        }
    }

    private fun enterRecordConsumption() {
        val input = EditText(this)
        input.hint = "Введіть новий розхід палива"

        AlertDialog.Builder(this)
            .setTitle("Оновлення розходу палива")
            .setMessage("Будь ласка, введіть розхід палива на 100 км:")
            .setView(input)
            .setPositiveButton("Зберегти") { dialog, _ ->
                val enteredData = input.text.toString()
                handleInputDataConsumption(enteredData)
                dialog.dismiss()
            }
            .setNegativeButton("Скасувати") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun handleInputDataConsumption(data: String) {
        val consumption = data.toFloatOrNull()
        if (consumption != null && consumption > 0) {
            newConsumption = consumption
            updateConsumtion()
        } else {
            Toast.makeText(this, "Введіть коректне число", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createLog(message: String){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = LocalDateTime.now().format(formatter)
        FileLogger.log("${formattedDateTime} | $message")
    }
}