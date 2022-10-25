package dev.hnatiuk.carscanner.presentation.pages.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.presentation.extensions.enableBackButton
import dev.hnatiuk.carscanner.presentation.extensions.makeToolbarAsActionBar
import dev.hnatiuk.carscanner.presentation.extensions.onBackPressedHandler

internal abstract class BaseToolbarActivity<V : ViewDataBinding, VM : BaseViewModel> :
    BaseActivity<V, VM>() {

    protected open val backButtonIconRes = R.drawable.ic_back_black

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar()
    }

    private fun setToolbar() {
        findViewById<Toolbar>(R.id.toolbar)?.let {
            makeToolbarAsActionBar(it)
            enableBackButton(backButtonIconRes)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = onBackPressedHandler(item)
}