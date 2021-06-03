package com.example.mvphomework

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}

fun Fragment.toast(message: String) = Toast
    .makeText(context, message, Toast.LENGTH_SHORT)
    .show()