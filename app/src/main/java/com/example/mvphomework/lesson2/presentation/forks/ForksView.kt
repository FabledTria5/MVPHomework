package com.example.mvphomework.lesson2.presentation.forks

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.Skip

interface ForksView : MvpView {

    @Skip
    fun showLoading()

    @Skip
    fun showError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showForks()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun init()
}
