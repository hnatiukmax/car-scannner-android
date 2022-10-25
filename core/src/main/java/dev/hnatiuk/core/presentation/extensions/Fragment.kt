package dev.hnatiuk.core.presentation.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    requireContext().toast(message, length)
}

fun Fragment.toast(messageRes: Int, length: Int = Toast.LENGTH_SHORT) {
    requireContext().toast(getString(messageRes), length)
}

fun Fragment.dpToPx(value: Int): Int {
    return requireContext().dpToPx(value)
}