package dev.hnatiuk.carscanner.presentation.extensions

import android.widget.TextView
import kotlin.reflect.KProperty

internal class TextViewValue(private val textView: TextView) {

    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return textView.text?.toString() ?: ""
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        textView.text = value
    }
}