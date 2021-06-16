package com.example.mvphomework.lesson2.data.retrofit.datasource.cloud

import com.example.mvphomework.lesson2.data.retrofit.network.RetrofitDataSource
import com.example.mvphomework.lesson2.data.retrofit.repository.GitHubRepositoryItem
import com.example.mvphomework.lesson2.data.retrofit.user.GitHubUser
import io.reactivex.rxjava3.core.Single

class CloudDataSource(private val api: RetrofitDataSource) : ICloudDataSource {

    override fun getUsers() = api.getUsers()

    override fun getRepositories(userName: String) = api.getRepositories(userName = userName)


}