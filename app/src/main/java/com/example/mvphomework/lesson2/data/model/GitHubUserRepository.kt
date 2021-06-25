package com.example.mvphomework.lesson2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GitHubUser::class,
            parentColumns = ["login"],
            childColumns = ["user_login"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    tableName = "github_user_repositories"
)
data class GitHubUserRepository(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @ColumnInfo(name = "user_login")
    @Transient
    var userLogin: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @SerializedName("forks_url")
    val forksUrl: String
)