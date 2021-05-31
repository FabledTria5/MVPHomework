package com.example.mvphomework.lesson2.presentation.users

interface IListPresenter<V : ItemView> {
    var itemClickListener: ((position: Int) -> Unit)?
    fun bindView(view: V, position: Int)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>