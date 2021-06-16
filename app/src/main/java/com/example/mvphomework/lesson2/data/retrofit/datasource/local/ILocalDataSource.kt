package com.example.mvphomework.lesson2.data.retrofit.datasource.local

import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubRepository
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubUser
import com.example.mvphomework.lesson2.data.retrofit.repository.GitHubRepositoryItem
import io.reactivex.rxjava3.core.Single

interface ILocalDataSource {
    fun getUsers(): Single<List<RoomGitHubUser>>
    fun getRepositories(userName: String): Single<List<RoomGitHubRepository>>
    fun getUserByLogin(userLogin: String): Single<RoomGitHubUser>

    fun saveUsers(roomUsers: List<RoomGitHubUser>)
    fun saveRepositories(roomRepositories: List<RoomGitHubRepository>)
}