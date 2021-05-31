package com.example.mvphomework.lesson2.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    val login: String
) : Parcelable