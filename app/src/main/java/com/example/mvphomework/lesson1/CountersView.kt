package com.example.mvphomework.lesson1

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

interface CountersView : MvpView {

    @OneExecution
    fun showOnBoarding()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCounter1(counter: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCounter2(counter: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCounter3(counter: String)

    @Skip
    fun showCounterMessage()
}