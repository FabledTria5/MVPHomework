package com.example.mvphomework.lesson2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubRepository
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubUser

@Database(
    entities = [RoomGitHubUser::class, RoomGitHubRepository::class],
    version = 1
)
abstract class GitHubDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao
}