package com.example.mvphomework.lesson2.data.datasource.local

import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import com.example.mvphomework.lesson2.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface ILocalDataSource {
    fun getUsers(): Single<List<GitHubUser>>
    fun getRepositories(userName: String): Single<List<GitHubUserRepository>>
    fun getUserByLogin(userLogin: String): Single<GitHubUser>

    fun fetchUsers(gitHubUsers: List<GitHubUser>): Single<List<GitHubUser>>
    fun fetchRepositories(roomRepositories: List<GitHubUserRepository>, userLogin: String):
            Single<List<GitHubUserRepository>>
}