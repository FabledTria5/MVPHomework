package com.example.mvphomework.lesson2.navigation

import com.example.mvphomework.lesson2.data.user.GitHubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface IScreens {
    fun users() : Screen
    fun user(gitHubUser: GitHubUser) : Screen
    fun forks(userName: String, repositoryName: String): FragmentScreen
}