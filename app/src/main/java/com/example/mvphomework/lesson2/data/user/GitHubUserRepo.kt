package com.example.mvphomework.lesson2.data.user

import io.reactivex.rxjava3.core.Single

interface GitHubUserRepo {
    fun getUsers(): Single<List<GitHubUser>>
}