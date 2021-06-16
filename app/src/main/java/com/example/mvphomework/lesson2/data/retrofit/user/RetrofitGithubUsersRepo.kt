package com.example.mvphomework.lesson2.data.retrofit.user

import com.example.mvphomework.lesson2.data.retrofit.datasource.cloud.ICloudDataSource
import com.example.mvphomework.lesson2.data.retrofit.datasource.local.ILocalDataSource
import com.example.mvphomework.lesson2.data.retrofit.repository.DataMapper
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val newtWorkStatus: NetworkStatus,
    private val cloudDataSource: ICloudDataSource,
    private val localDataSource: ILocalDataSource,
) : IUserRepository {

    private val mapper = DataMapper

    override fun getUsers(): Single<List<GitHubUser>> =
        newtWorkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                val users = cloudDataSource.getUsers()
                localDataSource.saveUsers(mapper.map(users.blockingGet()))
                users
            } else {
                localDataSource.getUsers().flatMap {
                    mapper.map(it)
                }
            }
        }.subscribeOn(Schedulers.io())
}