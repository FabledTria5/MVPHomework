package com.example.mvphomework.lesson2.presentation.users

import com.example.mvphomework.lesson2.data.user.GitHubUserRepository

class UserInteractor(private val userRepository: GitHubUserRepository) {
    fun getUsers() = userRepository.getUsers()
}