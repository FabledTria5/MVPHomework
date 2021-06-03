package com.example.mvphomework.lesson2.data.user

import io.reactivex.rxjava3.core.Observable

class GitHubUserRepository {

    private val usersList = listOf(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUsers(): Observable<List<GitHubUser>> = Observable.just(usersList)
}