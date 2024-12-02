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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = DatabaseProvider.getDatabase(this)
        val carDao = db.carDao()



        val myText: TextView = findViewById(R.id.textV)
        findViewById<Button>(R.id.btn).setOnClickListener{
            showInputDialog()
        }

        

        lifecycleScope.launch(Dispatchers.IO) {
          // --- Викликаємо suspend-функції всередині корутини
          //  val newCar = CarEntity(0, 15, "2024-10-10", 15, "2020-09-01", 14)
          // carDao.insertCar(newCar)
          //  carDao.updateMileage(120)
          // val currentDate: LocalDate = LocalDate.now()
          //  val dateAsString: String = currentDate.toString()
          // carDao.updateOilChangeDate(dateAsString)
            val mileage = carDao.getLastOilChangeDate()

            // --- Оновлюємо UI в головному потоці
            launch(Dispatchers.Main) {
                myText.text = mileage
            }
        }
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
        findViewById<TextView>(R.id.textV).text = "Введено: $data"
    }
}