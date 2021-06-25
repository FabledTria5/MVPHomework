package com.example.mvphomework.lesson2.data.db

import androidx.room.*
import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GitHubUserRepository): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: GitHubUserRepository): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GitHubUserRepository>): Completable

    @Update
    fun update(user: GitHubUserRepository)

    @Update
    fun update(vararg users: GitHubUserRepository)

    @Update
    fun update(users: List<GitHubUserRepository>)

    @Delete
    fun delete(user: GitHubUserRepository)

    @Delete
    fun delete(vararg users: GitHubUserRepository)

    @Delete
    fun delete(users: List<GitHubUserRepository>)

    @Query("SELECT * FROM github_user_repositories")
    fun getAll(): Single<List<GitHubUserRepository>>

    @Query("SELECT * FROM github_user_repositories WHERE user_login = :login")
    fun findForUser(login: String): Single<List<GitHubUserRepository>>
}
