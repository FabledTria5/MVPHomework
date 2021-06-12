package com.example.mvphomework.lesson2.data.network

import com.example.mvphomework.lesson2.data.fork.Fork
import com.example.mvphomework.lesson2.data.repository.RepositoriesList
import com.example.mvphomework.lesson2.data.user.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataSource {

    @GET(value = "/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET(value = "/users/{userName}/repos")
    fun getRepositories(@Path(value = "userName") userName: String): Single<RepositoriesList>

    @GET(value = "repos/{userName}/{repositoryName}/forks")
    fun getForks(
        @Path(value = "userName") userName: String,
        @Path(value = "repositoryName") repositoryName: String
    ) : Single<Fork>

}