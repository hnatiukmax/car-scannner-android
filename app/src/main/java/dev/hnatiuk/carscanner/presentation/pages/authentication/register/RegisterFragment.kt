package dev.hnatiuk.carscanner.presentation.pages.authentication.register

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.hnatiuk.carscanner.databinding.FragmentRegisterBinding
import dev.hnatiuk.carscanner.presentation.pages.authentication.AuthFragmentType
import dev.hnatiuk.carscanner.presentation.pages.authentication.OnAuthTypeChangeListener
import dev.hnatiuk.core.presentation.base.Inflate
import dev.hnatiuk.core.presentation.base.view.BaseFragment

@AndroidEntryPoint
internal class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterFragmentViewModel, RegisterEvent>() {

    override val viewModel: RegisterFragmentViewModel by viewModels()
    override val inflate: Inflate<FragmentRegisterBinding>
        get() = FragmentRegisterBinding::inflate

    companion object {
        fun newInstance() = RegisterFragment()
    }

    override fun FragmentRegisterBinding.initUI() {
        viewModel = this@RegisterFragment.viewModel

        haveAnAccount.setOnClickListener {
            (activity as? OnAuthTypeChangeListener)?.onAuthTypeChange(AuthFragmentType.LOGIN)
        }
    }
}