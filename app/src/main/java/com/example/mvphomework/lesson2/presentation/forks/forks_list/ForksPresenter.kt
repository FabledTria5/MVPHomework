package com.example.mvphomework.lesson2.presentation.forks.forks_list

interface ForksPresenter<V> {
    fun bindView(view: V, position: Int)
    fun getCount(): Int
}

interface IForksPresenter : ForksPresenter<ForksItemView>