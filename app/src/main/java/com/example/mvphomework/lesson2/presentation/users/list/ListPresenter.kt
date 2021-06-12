package com.example.mvphomework.lesson2.presentation.users

import com.example.mvphomework.lesson2.presentation.users.list.ItemView
import com.example.mvphomework.lesson2.presentation.users.list.UserItemView

interface IListPresenter<V : ItemView> {
    var itemClickListener: ((position: Int) -> Unit)?
    fun bindView(view: V, position: Int)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>