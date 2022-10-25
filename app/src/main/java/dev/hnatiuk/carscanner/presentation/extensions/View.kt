@file:Suppress("NOTHING_TO_INLINE", "unused")
package dev.hnatiuk.carscanner.presentation.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import org.jetbrains.anko.dip
import java.util.*

/*
 * View properties
 **/
internal fun View.visible() {
    this.visibility = View.VISIBLE
}

internal fun View.gone() {
    this.visibility = View.GONE
}

internal fun View.generateId() {
    id = Random().nextInt(Int.MAX_VALUE)
}

internal inline fun View.dip(@DimenRes dimenRes: Int): Int = context.dip(resources.getDimension(dimenRes))

fun View.getDrawable(@DrawableRes id: Int, default: ColorDrawable = ColorDrawable(Color.TRANSPARENT)) =
    ContextCompat.getDrawable(context, id) ?: default