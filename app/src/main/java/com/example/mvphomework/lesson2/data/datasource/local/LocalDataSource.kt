package com.example.mvphomework.lesson2.data.datasource.local

import com.example.mvphomework.lesson2.data.db.GitHubDatabase
import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import com.example.mvphomework.lesson2.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single

class LocalDataSource(private val db: GitHubDatabase) : ILocalDataSource {

    override fun getUsers(): Single<List<GitHubUser>> = db.userDao().getAll()

    override fun getRepositories(userName: String): Single<List<GitHubUserRepository>> =
        db.repositoryDao().findForUser(login = userName)

    override fun getUserByLogin(userLogin: String): Single<GitHubUser> =
        db.userDao().findByLogin(userLogin)

    override fun fetchUsers(gitHubUsers: List<GitHubUser>): Single<List<GitHubUser>> =
        db.userDao()
            .insert(gitHubUsers)
            .andThen(getUsers())

    override fun fetchRepositories(
        roomRepositories: List<GitHubUserRepository>,
        userLogin: String
    ): Single<List<GitHubUserRepository>> =
        db.repositoryDao()
            .insert(roomRepositories.map {
                it.userLogin = userLogin
                it
            })
            .andThen(db.repositoryDao().findForUser(userLogin))
}