package com.example.mvphomework.lesson2.di.modules

import android.content.Context
import com.example.mvphomework.lesson2.data.retrofit.network.RetrofitDataSource
import com.example.mvphomework.lesson2.utils.network.AndroidNetworkStatus
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun api(client: OkHttpClient): RetrofitDataSource =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitDataSource::class.java)

    @Singleton
    @Provides
    fun client() = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Singleton
    @Provides
    fun netWorkStatus(context: Context): NetworkStatus = AndroidNetworkStatus(context)
}