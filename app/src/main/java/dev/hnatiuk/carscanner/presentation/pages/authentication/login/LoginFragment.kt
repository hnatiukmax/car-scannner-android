package dev.hnatiuk.carscanner.presentation.pages.authentication.login

import dev.hnatiuk.carscanner.presentation.pages.authentication.AuthFragmentType
import dev.hnatiuk.carscanner.presentation.pages.authentication.OnAuthTypeChangeListener
import dev.hnatiuk.carscanner.presentation.pages.base.BaseFragment
import dev.hnatiuk.carscanner.presentation.pages.base.Depends
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.FragmentLoginBinding

@Depends(R.layout.fragment_login, LoginFragmentViewModel::class)
internal class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun FragmentLoginBinding.initUI() {
        viewModel = this@LoginFragment.viewModel

        noAccount.setOnClickListener {
            (activity as? OnAuthTypeChangeListener)?.onAuthTypeChange(AuthFragmentType.REGISTER)
        }
    }
}