package com.example.mvphomework

import com.example.mvphomework.lesson2.di.components.DaggerHomeworkComponent
import com.example.mvphomework.lesson2.schedulers.DefaultSchedulers
import com.example.mvphomework.lesson2.schedulers.Schedulers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MvpApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MvpApplication> =
        DaggerHomeworkComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(DefaultSchedulers)
            .build()
}
