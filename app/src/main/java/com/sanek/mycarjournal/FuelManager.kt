package com.sanek.mycarjournal

import java.util.LinkedList

class FuelManager {
    private val maxSize = 10
    private val lastTenRecords = LinkedList<Float>()

    constructor(){
        // загрузка з БД
    }

    fun inputRecord(record: Float){
        if (lastTenRecords.size >= maxSize){
            lastTenRecords.removeFirst()
            lastTenRecords.addLast(record)
        } else {
            lastTenRecords.addLast(record)
        }
        // update BD
    }
}