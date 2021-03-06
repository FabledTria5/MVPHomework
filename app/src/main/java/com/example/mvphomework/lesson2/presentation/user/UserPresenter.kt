package com.example.mvphomework.lesson2.presentation.user

import com.example.mvphomework.lesson2.data.datasource.repository.IRepoRepository
import com.example.mvphomework.lesson2.data.model.ForkItem
import com.example.mvphomework.lesson2.data.model.GitHubUser
import com.example.mvphomework.lesson2.data.model.GitHubUserRepository
import com.example.mvphomework.lesson2.navigation.IScreens
import com.example.mvphomework.lesson2.presentation.user.repos_list.IRepositoryListPresenter
import com.example.mvphomework.lesson2.presentation.user.repos_list.RepositoryItemView
import com.example.mvphomework.lesson2.schedulers.Schedulers
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class UserPresenter(
    private val gitHubUser: GitHubUser,
    private val schedulers: Schedulers,
    private val usersRepo: IRepoRepository,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UserView>() {

    private val disposables = CompositeDisposable()

    class RepositoryPresenter : IRepositoryListPresenter {

        val repositories = mutableListOf<GitHubUserRepository>()

        override var itemClickListener: ((position: Int) -> Unit)? = null

        override fun bindView(view: RepositoryItemView, position: Int) {
            val repository = repositories[position]
            repository.name.let { view.setName(it) }
        }

        override fun getCount() = repositories.count()
    }

    val repositoriesPresenter = RepositoryPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showUser(gitHubUser)
        loadRepositories()

        repositoriesPresenter.itemClickListener = { itemPosition ->
            val userName = gitHubUser.login
            val repositoryName = repositoriesPresenter.repositories[itemPosition].name
            router.navigateTo(
                screen = screens.forks(
                    userName = userName,
                    repositoryName = repositoryName
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun loadRepositories() {
        disposables += usersRepo.getRepositories(gitHubUser.login)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetRepositoriesSuccess),
                onError = (::onGetRepositoriesError)
            )
    }

    private fun onGetRepositoriesSuccess(repositoriesList: List<GitHubUserRepository>) {
        repositoriesPresenter.repositories.addAll(repositoriesList)
        viewState.updateList()
    }

    private fun onGetRepositoriesError(throwable: Throwable) = viewState.showError(throwable)

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}