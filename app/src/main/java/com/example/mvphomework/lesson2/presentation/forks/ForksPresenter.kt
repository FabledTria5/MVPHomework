package com.example.mvphomework.lesson2.presentation.forks

import com.example.mvphomework.lesson2.data.datasource.fork.ForksRepository
import com.example.mvphomework.lesson2.data.model.Fork
import com.example.mvphomework.lesson2.data.model.ForkItem
import com.example.mvphomework.lesson2.presentation.forks.forks_list.ForksItemView
import com.example.mvphomework.lesson2.presentation.forks.forks_list.IForksPresenter
import com.example.mvphomework.lesson2.schedulers.Schedulers
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class ForksPresenter(
    private val forksUrlData: Pair<String, String>,
    private val forksRepository: ForksRepository,
    private val schedulers: Schedulers,
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
        if (forksUrlData.first.isEmpty() or forksUrlData.second.isEmpty()) {
            onGetForksError()
            return
        }
        disposables += forksRepository.getForks(forksUrlData.first, forksUrlData.second)
            .observeOn(schedulers.main())
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