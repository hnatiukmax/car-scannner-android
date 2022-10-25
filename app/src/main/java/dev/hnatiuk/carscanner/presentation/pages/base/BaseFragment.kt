package dev.hnatiuk.carscanner.presentation.pages.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import dev.hnatiuk.carscanner.domain.core.StringResource
import dev.hnatiuk.carscanner.presentation.extensions.hideSoftKeyboard
import dev.hnatiuk.core.presentation.extensions.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Suppress("UNCHECKED_CAST")
internal abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment(),
    BaseView<V, VM> {

    private val baseActivity get() = (activity as? BaseActivity<ViewDataBinding, BaseViewModel>)

    protected lateinit var binding: V
    protected val viewModel by lazy { getViewModel(viewModelClass) as VM }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(DataBindingUtil.inflate<V>(layoutInflater, layoutRes, container, false)) {
        binding = this
        lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        initUI()
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.apply {
            onShowMessage.observe {
                when (it) {
                    is String -> toast(it)
                    is StringResource -> toast(it.messageResId)
                }
            }
            onShowError.observe { toast(it.message ?: getString(it.messageResource.messageResId)) }
            onCloseKeyboard.observe { baseActivity?.hideSoftKeyboard() }
            observeViewModel()
        }
    }

    protected inline fun <P> LiveData<P>.observe(crossinline observerBody: (P) -> Unit) {
        observe(viewLifecycleOwner) {
            observerBody(it)
        }
    }
}