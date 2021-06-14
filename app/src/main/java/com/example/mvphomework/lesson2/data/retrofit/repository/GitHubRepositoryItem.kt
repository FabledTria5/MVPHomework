package com.example.mvphomework.lesson2.data.retrofit.repository

data class GitHubRepositoryItem(
    val id: Int,
    val name: String,
    val forks_count: Int,
    val forks_url: String,
)