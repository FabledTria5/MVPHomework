package com.example.mvphomework

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    fun increase(index: Int) = ++counters[index]
}