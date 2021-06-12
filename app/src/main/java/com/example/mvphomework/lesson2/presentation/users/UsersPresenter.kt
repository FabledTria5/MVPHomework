package com.example.mvphomework.lesson2.presentation.users

import android.util.Log
import com.example.mvphomework.lesson2.data.user.GitHubUser
import com.example.mvphomework.lesson2.navigation.AndroidScreens
import com.example.mvphomework.lesson2.presentation.users.list.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class UsersPresenter(
    private val userInteractor: UserInteractor,
    private val router: Router
) : MvpPresenter<UsersView>() {

    companion object {
        private const val TAG = "UsersPresenter"
    }

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

    private fun loadData() = userInteractor.getUsers().toObservable().subscribeBy(
        onNext = ::onGetUsersSuccess,
        onError = ::onGetUsersError,
    )

    private fun onGetUsersSuccess(users: List<GitHubUser>) {
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    private fun onGetUsersError(t: Throwable) = viewState.showError().run {
        Log.d(TAG, "onGetUsersError: $t")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}