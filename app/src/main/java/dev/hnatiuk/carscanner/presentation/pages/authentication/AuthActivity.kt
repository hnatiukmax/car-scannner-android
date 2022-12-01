package dev.hnatiuk.carscanner.presentation.pages.authentication

import androidx.activity.viewModels
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.ActivityAuthBinding
import dev.hnatiuk.carscanner.presentation.enum.AnimationType
import dev.hnatiuk.carscanner.presentation.extensions.setTransition
import dev.hnatiuk.carscanner.presentation.pages.authentication.login.LoginFragment
import dev.hnatiuk.carscanner.presentation.pages.authentication.register.RegisterFragment
import dev.hnatiuk.core.presentation.base.LayoutInflate
import dev.hnatiuk.core.presentation.base.view.BaseActivity

@AndroidEntryPoint
internal class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel, AuthEvent>(),
    OnAuthTypeChangeListener {

    override val bindingFactory: LayoutInflate<ActivityAuthBinding>
        get() = ActivityAuthBinding::inflate

    override val viewModel: AuthViewModel by viewModels()

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