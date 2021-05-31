package com.example.mvphomework.lesson2.navigation

import com.example.mvphomework.lesson2.data.user.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users() : Screen
    fun user(gitHubUser: GitHubUser) : Screen
}