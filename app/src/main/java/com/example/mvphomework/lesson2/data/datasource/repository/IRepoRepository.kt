package com.example.mvphomework.lesson2.data.datasource.repository

import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import io.reactivex.rxjava3.core.Single

interface IRepoRepository {
    fun getRepositories(userLogin: String): Single<List<GitHubUserRepository>>
}