package com.example.mvphomework.lesson2.data.db

import androidx.room.*
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubUser
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: RoomGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGitHubUser>)

    @Update
    fun update(user: RoomGitHubUser)

    @Update
    fun update(vararg users: RoomGitHubUser)

    @Update
    fun update(users: List<RoomGitHubUser>)

    @Delete
    fun delete(user: RoomGitHubUser)

    @Delete
    fun delete(vararg users: RoomGitHubUser)

    @Delete
    fun delete(users: List<RoomGitHubUser>)

    @Query(value = "SELECT * FROM RoomGitHubUser")
    fun getAll(): Single<List<RoomGitHubUser>>

    @Query(value = "SELECT * FROM RoomGitHubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): Single<RoomGitHubUser>?

}