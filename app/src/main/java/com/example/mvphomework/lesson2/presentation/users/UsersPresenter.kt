package com.example.mvphomework.lesson2.presentation.users

import com.example.mvphomework.lesson2.data.user.GitHubUser
import com.example.mvphomework.lesson2.data.user.GitHubUserRepository
import com.example.mvphomework.lesson2.navigation.AndroidScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepository: GitHubUserRepository,
    private val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((position: Int) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView, position: Int) {
            val user = users[position]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemPosition ->
            router.navigateTo(AndroidScreens().user(usersListPresenter.users[itemPosition]))
        }
    }

    private fun loadData() {
        val users = usersRepository.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}