package com.example.mvphomework.lesson2.data.retrofit.repository

import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubRepository
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubUser
import com.example.mvphomework.lesson2.data.retrofit.user.GitHubUser
import io.reactivex.rxjava3.core.Single

object DataMapper {

    @JvmName(name = "map")
    fun map(roomUsers: List<RoomGitHubUser>): Single<List<GitHubUser>> = Single.fromCallable {
        roomUsers.map {
            GitHubUser(
                it.id,
                it.login,
                it.avatarUrl,
                it.reposUrl
            )
        }
    }

    @JvmName("map1")
    fun map(roomRepositoryList: List<RoomGitHubRepository>): List<GitHubRepositoryItem> =
        roomRepositoryList.map {
            GitHubRepositoryItem(
                it.id.toInt(), it.name, it.forksCount, ""
            )
        }


    fun map(cloudUsers: List<GitHubUser>): List<RoomGitHubUser> =
        cloudUsers.map {
            RoomGitHubUser(it.id, it.login, it.avatar_url, it.repos_url)
        }

    @JvmName("map2")
    fun map(cloudRepos: List<GitHubRepositoryItem>, userId: String): List<RoomGitHubRepository> =
        cloudRepos.map {
            RoomGitHubRepository(it.id.toString(), it.name, it.forks_count, it.forks_url, userId)
        }
}