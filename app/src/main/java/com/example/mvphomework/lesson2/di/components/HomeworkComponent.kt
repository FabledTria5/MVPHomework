package com.example.mvphomework.lesson2.di.components

import android.content.Context
import com.example.mvphomework.MvpApplication
import com.example.mvphomework.lesson2.di.modules.*
import com.example.mvphomework.lesson2.schedulers.Schedulers
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
        UiModule::class,
        AndroidInjectionModule::class]
)
interface HomeworkComponent : AndroidInjector<MvpApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolders(navigationHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(schedule: Schedulers): Builder

        fun build(): HomeworkComponent
    }

}