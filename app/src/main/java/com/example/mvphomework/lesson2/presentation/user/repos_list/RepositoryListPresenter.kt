package com.example.mvphomework.lesson2.presentation.user.repos_list

import com.example.mvphomework.lesson2.data.fork.ForkItem
import com.example.mvphomework.lesson2.presentation.user.UserPresenter

interface RepositoryListPresenter<V> {
    var itemClickListener: ((position: Int) -> Unit)?
    fun bindView(view: V, position: Int)
    fun getCount(): Int
    fun setForks(forks: List<ForkItem>)
}

interface IRepositoryListPresenter : RepositoryListPresenter<RepositoryItemView>