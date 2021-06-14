package com.example.mvphomework.lesson2.presentation.forks

import com.example.mvphomework.lesson2.data.retrofit.fork.Fork
import com.example.mvphomework.lesson2.data.retrofit.fork.ForkItem
import com.example.mvphomework.lesson2.data.retrofit.fork.RetrofitForksRepository
import com.example.mvphomework.lesson2.presentation.forks.forks_list.ForksItemView
import com.example.mvphomework.lesson2.presentation.forks.forks_list.IForksPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class ForksPresenter(
    private val forksUrlData: Pair<String, String>,
    private val forksRepository: RetrofitForksRepository,
    private val uiScheduler: Scheduler,
    private val router: Router,
) : MvpPresenter<ForksView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadForks()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    class ForksListPresenter : IForksPresenter {

        val forks = mutableListOf<ForkItem>()

        override fun bindView(view: ForksItemView, position: Int) {
            forks[position].full_name.let { view.setFork(it) }
        }

        override fun getCount() = forks.count()
    }

    val forksListPresenter = ForksListPresenter()

    private fun loadForks() {
        viewState.showLoading()
        disposables += forksRepository.getForks(forksUrlData.first, forksUrlData.second)
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = { onGetForksSuccess(it) },
                onError = { onGetForksError() }
            )
    }

    private fun onGetForksSuccess(fork: Fork) {
        forksListPresenter.forks.addAll(fork)
        viewState.showForks()
    }

    private fun onGetForksError() = viewState.showError()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}