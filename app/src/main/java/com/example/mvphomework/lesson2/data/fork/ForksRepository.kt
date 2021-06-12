package com.example.mvphomework.lesson2.data.fork

import io.reactivex.rxjava3.core.Single
import kotlin.String

interface ForksRepository {
    fun getForks(userName: String, repositoryName: String) : Single<Fork>
}