package com.example.mvphomework.lesson2.presentation.user.repos_list

import com.example.mvphomework.lesson2.data.model.ForkItem

interface RepositoryListPresenter<V> {
    var itemClickListener: ((position: Int) -> Unit)?
    fun bindView(view: V, position: Int)
    fun getCount(): Int
}

interface IRepositoryListPresenter : RepositoryListPresenter<RepositoryItemView>