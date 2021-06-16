package com.example.mvphomework.lesson2.data.db

import androidx.room.*
import com.example.mvphomework.lesson2.data.db.entity.RoomGitHubRepository
import io.reactivex.rxjava3.core.Single

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGitHubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGitHubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGitHubRepository>)

    @Update
    fun update(user: RoomGitHubRepository)

    @Update
    fun update(vararg users: RoomGitHubRepository)

    @Update
    fun update(users: List<RoomGitHubRepository>)

    @Delete
    fun delete(user: RoomGitHubRepository)

    @Delete
    fun delete(vararg users: RoomGitHubRepository)

    @Delete
    fun delete(users: List<RoomGitHubRepository>)

    @Query("SELECT * FROM RoomGithubRepository")
    fun getAll(): Single<List<RoomGitHubRepository>>

    @Query("SELECT * FROM RoomGithubRepository WHERE userId = :id")
    fun findForUser(id: String): Single<List<RoomGitHubRepository>>
}
