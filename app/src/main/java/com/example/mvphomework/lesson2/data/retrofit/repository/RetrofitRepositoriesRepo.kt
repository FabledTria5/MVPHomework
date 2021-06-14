package com.example.mvphomework.lesson2.data.retrofit.repository

import com.example.mvphomework.lesson2.data.db.GitHubDatabase
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubRepository
import com.example.mvphomework.lesson2.data.retrofit.network.DataSource
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepositoriesRepo(
    private val api: DataSource,
    private val networkStatus: NetworkStatus,
    private val db: GitHubDatabase
) : RepositoriesRepo {

    override fun getRepositories(userLogin: String): Single<List<GitHubRepositoryItem>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getRepositories(userLogin).flatMap { repositories ->
                    val roomUser = db.userDao().findByLogin(userLogin)
                        ?: throw RuntimeException("No such user in cache")
                    val roomRepos = repositories.map {
                        RoomGitHubRepository(
                            it.id.toString(),
                            it.name,
                            it.forks_count,
                            it.forks_url,
                            roomUser.blockingGet().id
                        )
                    }
                    db.repositoryDao().insert(roomRepos)
                    Single.just(repositories)
                }
                    ?: Single.error<List<GitHubRepositoryItem>>(RuntimeException("User has no repos url"))
                        .subscribeOn(
                            Schedulers.io()
                        )
            } else {
                val roomUser = db.userDao().findByLogin(userLogin)
                    ?: throw RuntimeException("No such user in cache")
                db.repositoryDao().findForUser(roomUser.blockingGet().id)
                    .map { repositoriesList ->
                        repositoriesList.map {
                            GitHubRepositoryItem(it.id.toInt(), it.name, it.forksCount, "")
                        }
                    }
            }
        }.subscribeOn(Schedulers.io())

}