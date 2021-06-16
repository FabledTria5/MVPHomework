package com.example.mvphomework.lesson2.data.retrofit.repository

import com.example.mvphomework.lesson2.data.retrofit.datasource.cloud.ICloudDataSource
import com.example.mvphomework.lesson2.data.retrofit.datasource.local.ILocalDataSource
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepositoriesRepo(
    private val networkStatus: NetworkStatus,
    private val cloudDataSource: ICloudDataSource,
    private val localDataSource: ILocalDataSource,
) : IRepoRepository {

    private val mapper = DataMapper

    override fun getRepositories(userLogin: String): Single<List<GitHubRepositoryItem>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cloudDataSource.getRepositories(userLogin).flatMap { repositories ->
                    localDataSource.saveRepositories(
                        mapper.map(
                            repositories,
                            localDataSource.getUserByLogin(userLogin).blockingGet().id
                        )
                    )
                    Single.just(repositories)
                }
            } else {
                val roomUser = localDataSource.getUserByLogin(userLogin)
                localDataSource.getRepositories(roomUser.blockingGet().id)
                    .map { repositoriesList ->
                        mapper.map(repositoriesList)
                    }
            }
        }.subscribeOn(Schedulers.io())
}