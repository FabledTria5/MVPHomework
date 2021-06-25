package com.example.mvphomework.lesson2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "github_users")
data class GitHubUser(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @PrimaryKey
    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String,
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar_url")
    val avatar: String,
) : Parcelable