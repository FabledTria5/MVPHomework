package com.example.mvphomework.lesson2.presentation.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersView : MvpView {

    @AddToEndSingle
    fun init()

    @AddToEndSingle
    fun updateList()

    @AddToEndSingle
    fun showError(t: Throwable)
}