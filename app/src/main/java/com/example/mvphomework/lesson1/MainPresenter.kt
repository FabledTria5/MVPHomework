package com.example.mvphomework.lesson1

import moxy.MvpPresenter

class MainPresenter(private val model: CountersModel): MvpPresenter<CountersView>() {

    override fun onFirstViewAttach() {
        viewState.showCounter1(CountersMapper.map(0))
        viewState.showCounter2(CountersMapper.map(0))
        viewState.showCounter3(CountersMapper.map(0))

        viewState.showOnBoarding()
    }

    fun incrementCounter1() =
        model.increase(counterId = 0)
            .let(CountersMapper::map)
            .let(viewState::showCounter1)
            .also { viewState.showCounterMessage() }

    fun incrementCounter2() =
        model.increase(counterId = 1)
            .let(CountersMapper::map)
            .let(viewState::showCounter2)
            .also { viewState.showCounterMessage() }

    fun incrementCounter3() =
        model.increase(counterId = 2)
            .let(CountersMapper::map)
            .let(viewState::showCounter3)
            .also { viewState.showCounterMessage() }

}