package com.example.mvphomework.lesson2.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGitHubRepository(
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: Int,
    var forksUrl: String,
    var userId: String
)