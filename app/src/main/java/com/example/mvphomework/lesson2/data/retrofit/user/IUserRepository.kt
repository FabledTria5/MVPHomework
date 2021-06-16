package com.example.mvphomework.lesson2.data.retrofit.user

import io.reactivex.rxjava3.core.Single

interface IUserRepository {
    fun getUsers(): Single<List<GitHubUser>>
}