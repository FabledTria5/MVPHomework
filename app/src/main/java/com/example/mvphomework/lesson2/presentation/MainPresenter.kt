package com.example.mvphomework.lesson2.presentation

import com.example.mvphomework.lesson2.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    fun backClicked() = router.exit()

}