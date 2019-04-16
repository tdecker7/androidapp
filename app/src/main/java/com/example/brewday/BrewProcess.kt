package com.example.brewday

import java.util.*

class BrewProcess(var id: String = UUID.randomUUID().toString(),
                  val name: String,
                  val type: String) {

    fun addProcessToList(processesList: ArrayList<BrewProcess>, process: BrewProcess) {
        processesList.add(process)
    }

    fun type() = this.type
}
