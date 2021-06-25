package com.example.mvphomework.lesson2.data.datasource.cloud

import com.example.mvphomework.lesson2.data.retrofit.network.RetrofitDataSource

class CloudDataSource(private val api: RetrofitDataSource) : ICloudDataSource {

    override fun getUsers() = api.getUsers()

    override fun getRepositories(userLogin: String) = api.getRepositories(userName = userLogin)

}