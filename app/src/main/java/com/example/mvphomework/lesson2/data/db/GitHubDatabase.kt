package com.example.mvphomework.lesson2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubRepository
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubUser

@Database(
    entities = [RoomGitHubUser::class, RoomGitHubRepository::class],
    version = 1,
    exportSchema = false
)
abstract class GitHubDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: GitHubDatabase? = null

        fun getDatabase(context: Context): GitHubDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GitHubDatabase::class.java,
                    "github_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}