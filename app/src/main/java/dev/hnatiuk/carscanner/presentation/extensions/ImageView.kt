package dev.hnatiuk.carscanner.presentation.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.imageResource
import kotlin.reflect.KProperty

internal class ImageViewBackground(private val textView: ImageView) {

    @DrawableRes
    private var imageResId: Int? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = imageResId

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int?) {
        imageResId = value
        textView.backgroundResource = value ?: return
    }
}

internal class ImageViewSrc(private val textView: ImageView) {

    @DrawableRes
    private var imageResId: Int? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = imageResId

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int?) {
        imageResId = value
        textView.imageResource = value ?: return
    }
}