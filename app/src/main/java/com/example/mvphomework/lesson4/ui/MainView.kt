package com.example.mvphomework.lesson4.ui

import android.graphics.Bitmap
import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.Skip

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun setImage(imageUri: Uri)
    fun saveImage(bitmap: Bitmap)
    fun hideLoading()

    @Skip
    fun showLoading()

    @Skip
    fun showError()

    @Skip
    fun showSuccess()

    @Skip
    fun showCancel()

    @Skip
    fun loadImage()
}