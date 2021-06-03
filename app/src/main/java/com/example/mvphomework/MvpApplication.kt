package com.example.mvphomework

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class MvpApplication : Application() {

    object Navigation {

        private val cicerone by lazy { Cicerone.create() }

        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()
    }

}