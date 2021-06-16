package com.example.mvphomework.lesson2.data.retrofit.datasource.local

import com.example.mvphomework.lesson2.data.db.GitHubDatabase
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubRepository
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubUser
import com.example.mvphomework.lesson2.data.retrofit.user.GitHubUser
import io.reactivex.rxjava3.core.Single

class LocalDataSource(private val db: GitHubDatabase) : ILocalDataSource {

    override fun getUsers(): Single<List<RoomGitHubUser>> = db.userDao().getAll()

    override fun getRepositories(userId: String): Single<List<RoomGitHubRepository>> =
        db.repositoryDao().findForUser(id = userId)

    override fun getUserByLogin(userLogin: String): Single<RoomGitHubUser> =
        db.userDao().findByLogin(userLogin)

    override fun saveUsers(roomUsers: List<RoomGitHubUser>) =
        db.userDao().insert(roomUsers)

    override fun saveRepositories(roomRepositories: List<RoomGitHubRepository>) =
        db.repositoryDao().insert(roomRepositories)

}