package com.example.mvphomework.lesson4.ui

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class MainPresenter(private val bitmapConverter: BitmapConverter) : MvpPresenter<MainView>() {

    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.hideLoading()
    }

    fun onPickClicked() = viewState.loadImage()

    fun onImageReceived(imageUri: String) {
        viewState.setImage(MainMapper.map(imageUri))
        bitmapConverter.onImageSelected(imageUri = imageUri)
    }

    fun onConvertClicked() {
        disposable = bitmapConverter.convertImage(bitmapConverter.getImageFromUri())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    viewState.showSuccess()
                    viewState.hideLoading()
                },
                onError = {
                    viewState.showError()
                }
            )
    }

    fun cancel() {
        disposable?.dispose()
        viewState.showCancel()
    }
}