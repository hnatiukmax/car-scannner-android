package dev.hnatiuk.carscanner.presentation.pages.welcome

import androidx.lifecycle.ViewModel
import dev.hnatiuk.carscanner.domain.entity.AuthProvider
import dev.hnatiuk.carscanner.presentation.common.ActionLiveData

internal class WelcomeActivityViewModel : ViewModel() {

    val onOpenSignInPage = ActionLiveData<Unit>()

    fun onSignInClick() {
        onOpenSignInPage.call()
    }

    fun onSkipNowClick() {
        //TODO to main screen.
    }

    fun onSocialAuthClick(authProvider: AuthProvider) {
        //TODO call social provider to get token
    }
}