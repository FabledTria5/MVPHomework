package com.example.mvphomework.lesson2.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    fun backClicked() = router.exit()

}