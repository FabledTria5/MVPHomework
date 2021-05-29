package com.example.mvphomework.lesson1

class CountersModel(private val counters: MutableList<Int> = mutableListOf(0, 0, 0)) {

    fun increase(counterId: Int) = ++counters[counterId]
}