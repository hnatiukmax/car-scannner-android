package dev.hnatiuk.carscanner.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import dev.hnatiuk.carscanner.presentation.view.InputEditTextView

@BindingAdapter("hasError")
internal fun hasError(view: InputEditTextView, isError: Boolean?) {
    if (isError != null) {
        view.hasError(isError)
    }
}

@BindingAdapter("text")
internal fun setText(view: InputEditTextView, text: String?) {
    if (text != null && view.text != text) {
        view.text = text
    }
}

@InverseBindingAdapter(attribute = "text")
internal fun getText(view: InputEditTextView): String {
    return view.text
}

@BindingAdapter("textAttrChanged")
internal fun setTextListener(view: InputEditTextView, listener: InverseBindingListener?) {
    view.onTextChangedListener = {
        listener?.onChange()
    }
}