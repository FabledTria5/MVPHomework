package com.example.mvphomework.lesson2.data.retrofit.repository

import io.reactivex.rxjava3.core.Single

interface IRepoRepository {
    fun getRepositories(userLogin: String): Single<List<GitHubRepositoryItem>>
}