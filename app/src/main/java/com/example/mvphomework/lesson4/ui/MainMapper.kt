package com.example.mvphomework.lesson4.ui

import android.net.Uri

object MainMapper {
    fun map(imageUri: String): Uri = Uri.parse(imageUri)
}