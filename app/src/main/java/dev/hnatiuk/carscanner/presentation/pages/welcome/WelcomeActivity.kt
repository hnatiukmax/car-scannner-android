package dev.hnatiuk.carscanner.presentation.pages.welcome

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import dev.hnatiuk.carscanner.presentation.pages.authentication.AuthActivity
import dev.hnatiuk.core.presentation.base.compose.BaseComposeActivity
import dev.hnatiuk.core.presentation.extensions.toast

@AndroidEntryPoint
internal class WelcomeActivity : BaseComposeActivity<WelcomeViewModel, WelcomeEvent>() {

    override val viewModel: WelcomeViewModel by viewModels()

    @Composable
    override fun Compose() = WelcomeScreen()

    override fun WelcomeViewModel.observeViewModel() {
        onOpenSignInPage.observe(this@WelcomeActivity) {
            startActivity(Intent(this@WelcomeActivity, AuthActivity::class.java))
            //toast("Click test")
        }
    }
}