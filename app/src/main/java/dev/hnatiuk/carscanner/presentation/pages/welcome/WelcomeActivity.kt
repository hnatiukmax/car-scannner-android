package dev.hnatiuk.carscanner.presentation.pages.welcome

import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.hnatiuk.carscanner.databinding.ActivityWelcomeBinding
import dev.hnatiuk.carscanner.presentation.pages.authentication.AuthActivity
import dev.hnatiuk.core.presentation.base.LayoutInflate
import dev.hnatiuk.core.presentation.base.view.BaseActivity

@AndroidEntryPoint
internal class WelcomeActivity :
    BaseActivity<ActivityWelcomeBinding, WelcomeActivityViewModel, WelcomeEvent>() {

    override val viewModel: WelcomeActivityViewModel by viewModels()

    override val bindingFactory: LayoutInflate<ActivityWelcomeBinding>
        get() = ActivityWelcomeBinding::inflate

    override fun WelcomeActivityViewModel.observeViewModel() {
        onOpenSignInPage.observe(this@WelcomeActivity) {
            startActivity(Intent(this@WelcomeActivity, AuthActivity::class.java))
        }
    }
}