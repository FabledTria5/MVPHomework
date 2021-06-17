package com.example.mvphomework.lesson2.utils.images

interface ImageLoader<T> {
    fun loadInto(url: String?, container: T)
}