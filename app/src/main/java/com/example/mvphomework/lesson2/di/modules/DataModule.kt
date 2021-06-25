package com.example.mvphomework.lesson2.di.modules

import android.content.Context
import androidx.room.Room
import com.example.mvphomework.lesson2.data.datasource.cloud.CloudDataSource
import com.example.mvphomework.lesson2.data.datasource.cloud.ICloudDataSource
import com.example.mvphomework.lesson2.data.datasource.fork.ForksRepository
import com.example.mvphomework.lesson2.data.datasource.fork.RetrofitForksRepository
import com.example.mvphomework.lesson2.data.datasource.local.ILocalDataSource
import com.example.mvphomework.lesson2.data.datasource.local.LocalDataSource
import com.example.mvphomework.lesson2.data.datasource.repository.IRepoRepository
import com.example.mvphomework.lesson2.data.datasource.repository.RetrofitRepositoriesRepo
import com.example.mvphomework.lesson2.data.datasource.user.IUserRepository
import com.example.mvphomework.lesson2.data.datasource.user.RetrofitGithubUsersRepo
import com.example.mvphomework.lesson2.data.db.GitHubDatabase
import com.example.mvphomework.lesson2.data.retrofit.network.RetrofitDataSource
import com.example.mvphomework.lesson2.schedulers.DefaultSchedulers
import com.example.mvphomework.lesson2.schedulers.Schedulers
import com.example.mvphomework.lesson2.utils.Constants
import com.example.mvphomework.lesson2.utils.network.NetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [UiModule::class, NetworkModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideDatabaseName(): String = Constants.DATABASE_NAME

    @Singleton
    @Provides
    fun provideDataBase(
        context: Context,
        databaseName: String
    ): GitHubDatabase =
        Room.databaseBuilder(context, GitHubDatabase::class.java, databaseName).build()

    @Singleton
    @Provides
    fun provideLocalDataSource(db: GitHubDatabase): ILocalDataSource = LocalDataSource(db)

    @Singleton
    @Provides
    fun provideCloudDataSource(api: RetrofitDataSource): ICloudDataSource = CloudDataSource(api)

    @Singleton
    @Provides
    fun provideUsersRepo(
        netWorkStatus: NetworkStatus,
        cloudDataSource: ICloudDataSource,
        localDataSource: ILocalDataSource
    ): IUserRepository = RetrofitGithubUsersRepo(netWorkStatus, cloudDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideRepositoriesRepo(
        netWorkStatus: NetworkStatus,
        cloudDataSource: ICloudDataSource,
        localDataSource: ILocalDataSource
    ): IRepoRepository = RetrofitRepositoriesRepo(netWorkStatus, cloudDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideForksRepository(
        api: RetrofitDataSource
    ): ForksRepository = RetrofitForksRepository(api)

}