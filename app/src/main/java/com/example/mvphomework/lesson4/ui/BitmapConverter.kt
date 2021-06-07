package com.example.mvphomework.lesson4.ui

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class BitmapConverter(private val context: Context) {

    private var imageUri = ""

    fun onImageSelected(imageUri: String) {
        this.imageUri = imageUri
    }

    fun convertImage(bitmap: Bitmap, quality: Int = 100): Completable = Completable.fromAction {
        context.let {
            val file = File(context.getExternalFilesDir(null), "ConvertedImage.png")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, stream)
        }
    }.subscribeOn(Schedulers.io())

    fun getImageFromUri(): Bitmap =
        MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.parse(imageUri))
}