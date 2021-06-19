package com.example.mvphomework

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}

fun Fragment.toast(message: String) = Toast
    .makeText(context, message, Toast.LENGTH_SHORT)
    .show()

fun Activity.toast(message: String) = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .show()

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(avatarUrl: String) {
    Glide.with(this.context).asBitmap().load(avatarUrl).into(this)
}