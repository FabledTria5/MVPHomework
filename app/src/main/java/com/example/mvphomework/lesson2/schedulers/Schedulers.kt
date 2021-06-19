package com.example.mvphomework.lesson2.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface Schedulers {
    fun background(): Scheduler
    fun main(): Scheduler
}