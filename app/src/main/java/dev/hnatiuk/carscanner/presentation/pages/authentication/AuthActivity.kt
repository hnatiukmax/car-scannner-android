package dev.hnatiuk.carscanner.presentation.pages.authentication

import androidx.fragment.app.commit
import dev.hnatiuk.carscanner.presentation.pages.base.BaseToolbarActivity
import dev.hnatiuk.carscanner.presentation.pages.base.Depends
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.ActivityAuthBinding
import dev.hnatiuk.carscanner.presentation.enum.AnimationType
import dev.hnatiuk.carscanner.presentation.extensions.setTransition
import dev.hnatiuk.carscanner.presentation.pages.authentication.login.LoginFragment
import dev.hnatiuk.carscanner.presentation.pages.authentication.register.RegisterFragment

@Depends(R.layout.activity_auth, AuthViewModel::class)
internal class AuthActivity : BaseToolbarActivity<ActivityAuthBinding, AuthViewModel>(),
    OnAuthTypeChangeListener {

    override fun ActivityAuthBinding.initUI() {
        viewModel = this@AuthActivity.viewModel
        onAuthTypeChange(AuthFragmentType.LOGIN)
    }

    override fun onAuthTypeChange(authFragmentType: AuthFragmentType) {
        val (fragment, anim) = when (authFragmentType) {
            AuthFragmentType.LOGIN -> LoginFragment.newInstance() to AnimationType.SLIDE_RIGHT
            AuthFragmentType.REGISTER -> RegisterFragment.newInstance() to AnimationType.SLIDE_LEFT
        }

        supportFragmentManager.commit {
            if (supportFragmentManager.fragments.isNotEmpty()) {
                setTransition(anim)
            }
            replace(R.id.fragment_container, fragment)
        }
    }
}