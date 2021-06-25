package com.example.mvphomework.lesson2.data.datasource.repository

import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import com.example.mvphomework.lesson2.data.datasource.cloud.ICloudDataSource
import com.example.mvphomework.lesson2.data.datasource.local.ILocalDataSource
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepositoriesRepo(
    private val networkStatus: NetworkStatus,
    private val cloudDataSource: ICloudDataSource,
    private val localDataSource: ILocalDataSource,
) : IRepoRepository {

    override fun getRepositories(userLogin: String): Single<List<GitHubUserRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cloudDataSource.getRepositories(userLogin).flatMap {
                    localDataSource.fetchRepositories(it, userLogin)
                }
            } else {
                localDataSource.getRepositories(userLogin)
            }
        }.subscribeOn(Schedulers.io())
}