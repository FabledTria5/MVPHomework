package com.example.mvphomework.lesson2.data.fork

import com.example.mvphomework.lesson2.data.network.DataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.String

class RetrofitForksRepository(private val api: DataSource) : ForksRepository {

    override fun getForks(userName: String, repositoryName: String): Single<Fork> = api.getForks(
        userName = userName,
        repositoryName = repositoryName
    ).subscribeOn(Schedulers.io())

}