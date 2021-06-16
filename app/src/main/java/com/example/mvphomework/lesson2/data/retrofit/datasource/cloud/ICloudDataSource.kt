package com.example.mvphomework.lesson2.data.retrofit.datasource.cloud

import com.example.mvphomework.lesson2.data.retrofit.repository.GitHubRepositoryItem
import com.example.mvphomework.lesson2.data.retrofit.user.GitHubUser
import io.reactivex.rxjava3.core.Single

interface ICloudDataSource {
    fun getUsers(): Single<List<GitHubUser>>
    fun getRepositories(userName: String): Single<List<GitHubRepositoryItem>>
}