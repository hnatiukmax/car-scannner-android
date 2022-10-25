package dev.hnatiuk.carscanner.presentation.bindingadapters

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import dev.hnatiuk.carscanner.presentation.extensions.gone
import dev.hnatiuk.carscanner.presentation.extensions.visible

@BindingAdapter("showProgress")
internal fun showProgress(view: ProgressBar, isVisible: Boolean) {
    if (view.visibility != View.VISIBLE && isVisible) {
        view.visible()
    } else {
        view.gone()
    }
}