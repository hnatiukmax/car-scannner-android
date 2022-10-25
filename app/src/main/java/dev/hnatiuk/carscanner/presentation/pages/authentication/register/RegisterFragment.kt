package dev.hnatiuk.carscanner.presentation.pages.authentication.register

import dev.hnatiuk.carscanner.presentation.pages.authentication.AuthFragmentType
import dev.hnatiuk.carscanner.presentation.pages.authentication.OnAuthTypeChangeListener
import dev.hnatiuk.carscanner.presentation.pages.base.BaseFragment
import dev.hnatiuk.carscanner.presentation.pages.base.Depends
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.FragmentRegisterBinding

@Depends(R.layout.fragment_register, RegisterFragmentViewModel::class)
internal class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterFragmentViewModel>() {

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