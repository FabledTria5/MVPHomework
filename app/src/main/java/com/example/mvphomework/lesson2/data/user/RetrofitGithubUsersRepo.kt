package com.example.mvphomework.lesson2.data.user

import com.example.mvphomework.lesson2.data.network.DataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: DataSource) : GitHubUserRepo {

    override fun getUsers(): Single<List<GitHubUser>> = api.getUsers().subscribeOn(Schedulers.io())

}