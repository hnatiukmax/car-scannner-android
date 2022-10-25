package dev.hnatiuk.carscanner.presentation.pages.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import dev.hnatiuk.carscanner.domain.core.StringResource
import dev.hnatiuk.carscanner.presentation.extensions.hideSoftKeyboard
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Suppress("UNCHECKED_CAST")
internal abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    BaseView<V, VM> {

    protected lateinit var binding: V
    protected open val viewModel by lazy { getViewModel(viewModelClass) as VM }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.apply {
            lifecycleOwner = this@BaseActivity
            initUI()
        }

        viewModel.apply {
            onShowMessage.observe(this@BaseActivity) {
                when (it) {
                    is String -> toast(it)
                    is StringResource -> toast(it.messageResId)
                }
            }
            onShowError.observe(this@BaseActivity) { toast(it.message ?: "") }
            onCloseKeyboard.observe(this@BaseActivity) { hideSoftKeyboard() }
            observeViewModel()
        }
    }

    protected inline fun <P> LiveData<P>.observe(crossinline observerBody: (P) -> Unit) {
        observe(this@BaseActivity) {
            observerBody(it)
        }
    }
}