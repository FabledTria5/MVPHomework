package com.example.mvphomework.lesson2.data.db

import androidx.room.*
import com.example.mvphomework.lesson2.data.model.GitHubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GitHubUser): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: GitHubUser): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GitHubUser>): Completable

    @Update
    fun update(user: GitHubUser)

    @Update
    fun update(vararg users: GitHubUser)

    @Update
    fun update(users: List<GitHubUser>)

    @Delete
    fun delete(user: GitHubUser)

    @Delete
    fun delete(vararg users: GitHubUser)

    @Delete
    fun delete(users: List<GitHubUser>)

    @Query(value = "SELECT * FROM github_users")
    fun getAll(): Single<List<GitHubUser>>

    @Query(value = "SELECT * FROM github_users WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): Single<GitHubUser>

}