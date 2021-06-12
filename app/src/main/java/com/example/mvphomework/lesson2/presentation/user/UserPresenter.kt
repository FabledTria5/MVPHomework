package com.example.mvphomework.lesson2.presentation.user

import com.example.mvphomework.lesson2.data.fork.ForkItem
import com.example.mvphomework.lesson2.data.fork.RetrofitForksRepository
import com.example.mvphomework.lesson2.data.repository.GitHubRepositoryItem
import com.example.mvphomework.lesson2.data.repository.RepositoriesList
import com.example.mvphomework.lesson2.data.repository.RetrofitRepositoriesRepo
import com.example.mvphomework.lesson2.data.user.GitHubUser
import com.example.mvphomework.lesson2.navigation.AndroidScreens
import com.example.mvphomework.lesson2.presentation.user.repos_list.IRepositoryListPresenter
import com.example.mvphomework.lesson2.presentation.user.repos_list.RepositoryItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class UserPresenter(
    private val gitHubUser: GitHubUser,
    private val uiScheduler: Scheduler,
    private val usersRepo: RetrofitRepositoriesRepo,
    private val forksRepository: RetrofitForksRepository,
    private val router: Router,
    private val screens: AndroidScreens
) :
    MvpPresenter<UserView>() {

    class RepositoryPresenter : IRepositoryListPresenter {

        val repositories = mutableListOf<GitHubRepositoryItem>()

        override var itemClickListener: ((position: Int) -> Unit)? = null

        override fun bindView(view: RepositoryItemView, position: Int) {
            val repository = repositories[position]
            repository.full_name.let { view.setName(it) }
        }

        override fun getCount() = repositories.count()

        override fun setForks(forks: List<ForkItem>) {

        }
    }

    val repositoriesPresenter = RepositoryPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showUser(gitHubUser)
        loadRepositories()

        repositoriesPresenter.itemClickListener = { itemPosition ->
            val userName = gitHubUser.login!!
            val repositoryName = repositoriesPresenter.repositories[itemPosition].name
            router.navigateTo(
                screen = screens.forks(
                    userName = userName,
                    repositoryName = repositoryName
                )
            )
        }
    }

    private fun loadRepositories() = usersRepo.getRepositories(gitHubUser.login.toString())
        .observeOn(uiScheduler)
        .subscribeBy(
            onSuccess = { onGetRepositoriesSuccess(it) },
            onError = { onGetRepositoriesError() }
        )

    private fun onGetRepositoriesSuccess(repositoriesList: RepositoriesList) {
        repositoriesPresenter.repositories.addAll(repositoriesList)
        viewState.updateList()
    }

    private fun onGetRepositoriesError() = viewState.showError()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}