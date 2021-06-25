package com.example.mvphomework.lesson2.data.datasource.user

import com.example.mvphomework.lesson2.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IUserRepository {
    fun getUsers(): Single<List<GitHubUser>>
}