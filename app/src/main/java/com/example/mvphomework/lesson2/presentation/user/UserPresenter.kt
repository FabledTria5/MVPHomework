package com.example.mvphomework.lesson2.presentation.user

import com.example.mvphomework.lesson2.data.user.GitHubUser
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val gitHubUser: GitHubUser, private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showUser(gitHubUser)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}