package com.example.mvphomework.lesson2.data.retrofit.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import retrofit2.http.Url

@Parcelize
data class GitHubUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatar_url: String? = null,
    @Url val repos_url: String? = null
) : Parcelable