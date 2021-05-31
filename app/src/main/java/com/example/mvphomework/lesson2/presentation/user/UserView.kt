package com.example.mvphomework.lesson2.presentation.user

import com.example.mvphomework.lesson2.data.user.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface UserView : MvpView {
    @SingleState
    fun showUser(gitHubUser: GitHubUser)
}