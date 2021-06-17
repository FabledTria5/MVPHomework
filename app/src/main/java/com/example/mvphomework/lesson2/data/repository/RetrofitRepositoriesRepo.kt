package com.example.mvphomework.lesson2.data.repository

import com.example.mvphomework.lesson2.data.network.DataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepositoriesRepo(private val api: DataSource) : RepositoriesRepo {

    override fun getRepositories(userName: String): Single<RepositoriesList> =
        api.getRepositories(userName = userName).subscribeOn(Schedulers.io())

}