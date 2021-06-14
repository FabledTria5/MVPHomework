package com.example.mvphomework.lesson2.data.retrofit.user

import com.example.mvphomework.lesson2.data.db.GitHubDatabase
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubUser
import com.example.mvphomework.lesson2.data.retrofit.network.DataSource
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: DataSource,
    private val networkState: NetworkStatus,
    private val db: GitHubDatabase
) : GitHubUserRepo {

    override fun getUsers(): Single<List<GitHubUser>> =
        networkState.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGitHubUser(
                                user.id ?: "",
                                user.login ?: "",
                                user.avatar_url ?: "",
                                user.repos_url ?: ""
                            )
                        }
                        db.userDao().insert(roomUsers)
                        users
                    }
                }
            } else {
                db.userDao().getAll().map { usersList ->
                    usersList.map { roomUser ->
                        GitHubUser(
                            roomUser.id,
                            roomUser.login,
                            roomUser.avatarUrl,
                            roomUser.reposUrl
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}