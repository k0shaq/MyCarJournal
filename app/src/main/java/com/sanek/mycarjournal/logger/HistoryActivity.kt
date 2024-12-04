package com.sanek.mycarjournal.logger

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanek.mycarjournal.logger.FileLogger
import com.sanek.mycarjournal.R

class HistoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private val historyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        recyclerView = findViewById(R.id.recyclerViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter(historyList)
        recyclerView.adapter = historyAdapter

        loadHistory()

        if (historyList.isEmpty()) {
            Toast.makeText(this, "Історія порожня", Toast.LENGTH_SHORT).show()
        }

        val btnClear = findViewById<Button>(R.id.clearButton)
        btnClear.setOnClickListener{
            FileLogger.clearHistory()
            historyList.clear()
            historyAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Історія очищена", Toast.LENGTH_SHORT).show()
        }

    }

    private fun loadHistory() {
        FileLogger.init(this)
        val logFile = FileLogger.getLogFile()
        if (logFile.exists()) {
            val logs = logFile.readLines()
            historyList.addAll(logs.reversed())
            historyAdapter.notifyDataSetChanged()
        }
    }
}
