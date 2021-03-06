package com.example.mvphomework.lesson2.presentation.users

import com.example.mvphomework.lesson2.data.model.GitHubUser
import com.example.mvphomework.lesson2.data.datasource.user.IUserRepository
import com.example.mvphomework.lesson2.navigation.AndroidScreens
import com.example.mvphomework.lesson2.navigation.IScreens
import com.example.mvphomework.lesson2.presentation.users.list.IUserListPresenter
import com.example.mvphomework.lesson2.presentation.users.list.UserItemView
import com.example.mvphomework.lesson2.schedulers.Schedulers
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter(
    private val schedulers: Schedulers,
    private val usersRepo: IUserRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    private val disposables = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((position: Int) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView, position: Int) {
            val user = users[position]
            user.login.let { view.setLogin(it) }
            user.avatar.let { view.setAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemPosition ->
            val user = usersListPresenter.users[itemPosition]
            router.navigateTo(screen = screens.user(user))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun loadData() {
        disposables += usersRepo.getUsers()
            .observeOn(schedulers.main())
            .subscribeBy(
                onError = { onGetUsersError(it) },
                onSuccess = { onGetUsersSuccess(it) }
            )
    }

    private fun onGetUsersSuccess(users: List<GitHubUser>) {
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    private fun onGetUsersError(t: Throwable) = viewState.showError(t)

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}