package dev.hnatiuk.carscanner.presentation.utils.listeners

import android.text.Editable
import android.text.TextWatcher

internal open class SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        /* Default implementation */
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        /* Default implementation */
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        /* Default implementation */
    }
}