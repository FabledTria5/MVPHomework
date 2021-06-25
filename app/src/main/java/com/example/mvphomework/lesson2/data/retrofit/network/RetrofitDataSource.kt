package com.example.mvphomework.lesson2.data.retrofit.network

import com.example.mvphomework.lesson2.data.model.Fork
import com.example.mvphomework.lesson2.data.model.GitHubUser
import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitDataSource {

    @GET(value = "/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET(value = "/users/{userName}/repos")
    fun getRepositories(@Path(value = "userName") userName: String): Single<List<GitHubUserRepository>>

    @GET(value = "repos/{userName}/{repositoryName}/forks")
    fun getForks(
        @Path(value = "userName") userName: String,
        @Path(value = "repositoryName") repositoryName: String
    ): Single<Fork>

}