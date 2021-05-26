package com.example.mvphomework

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterClick(tag: Int) = tag.let(model::increase).let {
        view.setButtonText(tag, it.toString())
    }
}