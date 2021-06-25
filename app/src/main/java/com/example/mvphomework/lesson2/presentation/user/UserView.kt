package com.example.mvphomework.lesson2.presentation.user

import com.example.mvphomework.lesson2.data.model.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface UserView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun init()

    @AddToEndSingle
    fun showUser(gitHubUser: GitHubUser)

    @Skip
    fun showError(t: Throwable)

    @AddToEndSingle
    fun updateList()
}