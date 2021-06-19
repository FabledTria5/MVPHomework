package com.example.mvphomework.lesson2.di.modules

import com.example.mvphomework.lesson2.presentation.MainActivity
import com.example.mvphomework.lesson2.presentation.forks.ForksFragment
import com.example.mvphomework.lesson2.presentation.user.UserFragment
import com.example.mvphomework.lesson2.presentation.users.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    abstract fun bindForksFragment(): ForksFragment

}