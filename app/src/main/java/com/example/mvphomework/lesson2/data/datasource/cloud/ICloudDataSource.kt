package com.example.mvphomework.lesson2.data.datasource.cloud

import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import com.example.mvphomework.lesson2.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface ICloudDataSource {
    fun getUsers(): Single<List<GitHubUser>>
    fun getRepositories(userLogin: String): Single<List<GitHubUserRepository>>
}