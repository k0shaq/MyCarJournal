package com.sanek.mycarjournal.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sanek.mycarjournal.R
import com.sanek.mycarjournal.data.CarEntity
import com.sanek.mycarjournal.data.DatabaseProvider
import androidx.lifecycle.lifecycleScope
import com.sanek.mycarjournal.data.CarRepositoryImpl
import com.sanek.mycarjournal.domain.GetAverageRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.GetLastRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.GetMileage
import com.sanek.mycarjournal.domain.GetNextOilChange
import com.sanek.mycarjournal.domain.GetNextTechnicalInspection
import com.sanek.mycarjournal.domain.InputRecordFuelAverageConsumption
import com.sanek.mycarjournal.domain.SetNewMileage
import com.sanek.mycarjournal.domain.UpdateOilChange
import com.sanek.mycarjournal.domain.UpdateTechnicalInspection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    private lateinit var carFacade: CarFacade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = DatabaseProvider.getDatabase(this)
        val carDao = db.carDao()
        val fuelDao = db.fuelDao()
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


    }

    private fun showInputDialog() {
        // Створюємо EditText для введення даних
        val input = EditText(this)
        input.hint = "Введіть пробіг"

        // Створюємо AlertDialog
        AlertDialog.Builder(this)
            .setTitle("Введення даних")
            .setMessage("Будь ласка, введіть пробіг автомобіля:")
            .setView(input) // Додаємо текстове поле в діалог
            .setPositiveButton("Зберегти") { dialog, _ ->
                val enteredData = input.text.toString()
                // Логіка після натискання кнопки "Зберегти"
                handleInputData(enteredData)
                dialog.dismiss()
            }
            .setNegativeButton("Скасувати") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun handleInputData(data: String) {
        // Обробка введених даних
        // findViewById<TextView>(R.id.textV).text = "Введено: $data"
    }
}