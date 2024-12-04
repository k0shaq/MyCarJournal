package com.sanek.mycarjournal.logger

import android.content.Context
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

object FileLogger {
    private const val FILE_NAME = "app_log.txt"
    private lateinit var context: Context

    fun init(context: Context) {
        FileLogger.context = context
    }

    fun getLogFile(): File {
        return File(context.filesDir, FILE_NAME)
    }

    fun log(message: String) {
        try {
            val logFile = getLogFile()
            val writer = BufferedWriter(FileWriter(logFile, true))
            writer.append(message)
            writer.append("\n")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun clearHistory() {
        try {
            val logFile = getLogFile()
            if (logFile.exists()) {
                BufferedWriter(FileWriter(logFile)).use { writer ->
                    writer.write("")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}