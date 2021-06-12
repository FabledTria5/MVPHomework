package com.example.mvphomework.lesson2.data.repository

import io.reactivex.rxjava3.core.Single

interface RepositoriesRepo {
    fun getRepositories(userName: String): Single<RepositoriesList>
}