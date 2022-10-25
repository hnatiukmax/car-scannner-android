package dev.hnatiuk.carscanner.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import dev.hnatiuk.carscanner.presentation.view.CarInfoParamView

@BindingAdapter("value")
internal fun setValue(view: CarInfoParamView, value: String?) {
    if (value != view.value) {
        view.value = value ?: ""
    }
}