package dev.hnatiuk.carscanner.presentation.pages.authentication.login

import android.content.Intent
import androidx.fragment.app.viewModels
import dev.hnatiuk.carscanner.databinding.FragmentLoginBinding
import dev.hnatiuk.carscanner.presentation.pages.authentication.AuthFragmentType
import dev.hnatiuk.carscanner.presentation.pages.authentication.OnAuthTypeChangeListener
import dev.hnatiuk.carscanner.presentation.pages.main.MainActivity
import dev.hnatiuk.core.presentation.base.Inflate
import dev.hnatiuk.core.presentation.base.view.BaseFragment

internal class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginFragmentViewModel, LoginEvent>() {

    override val viewModel: LoginFragmentViewModel by viewModels()
    override val inflate: Inflate<FragmentLoginBinding>
        get() = FragmentLoginBinding::inflate

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun LoginFragmentViewModel.observeViewModel() {
        openMain.observe(viewLifecycleOwner) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    override fun FragmentLoginBinding.initUI() {
        viewModel = this@LoginFragment.viewModel

        noAccount.setOnClickListener {
            (activity as? OnAuthTypeChangeListener)?.onAuthTypeChange(AuthFragmentType.REGISTER)
        }

        login.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }
}