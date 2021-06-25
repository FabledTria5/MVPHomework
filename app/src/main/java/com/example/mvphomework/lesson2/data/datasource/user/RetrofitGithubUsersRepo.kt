package com.example.mvphomework.lesson2.data.datasource.user

import com.example.mvphomework.lesson2.data.model.GitHubUser
import com.example.mvphomework.lesson2.data.datasource.cloud.ICloudDataSource
import com.example.mvphomework.lesson2.data.datasource.local.ILocalDataSource
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val netWorkStatus: NetworkStatus,
    private val cloudDataSource: ICloudDataSource,
    private val localDataSource: ILocalDataSource,
) : IUserRepository {

    override fun getUsers(): Single<List<GitHubUser>> =
        netWorkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cloudDataSource.getUsers().flatMap { users ->
                    localDataSource.fetchUsers(users)
                }
            } else {
                localDataSource.getUsers()
            }
        }.subscribeOn(Schedulers.io())
}