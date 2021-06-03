package com.example.mvphomework

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import timber.log.Timber

class MvpApplication : Application() {

    object Navigation {

        private val cicerone by lazy { Cicerone.create() }

        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}