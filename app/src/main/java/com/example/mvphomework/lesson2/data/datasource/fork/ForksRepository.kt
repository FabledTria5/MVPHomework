package com.example.mvphomework.lesson2.data.datasource.fork

import com.example.mvphomework.lesson2.data.model.Fork
import io.reactivex.rxjava3.core.Single
import kotlin.String

interface ForksRepository {
    fun getForks(userName: String, repositoryName: String) : Single<Fork>
}