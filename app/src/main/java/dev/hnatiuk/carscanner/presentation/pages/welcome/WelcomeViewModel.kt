package dev.hnatiuk.carscanner.presentation.pages.welcome

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hnatiuk.carscanner.domain.entity.AuthProvider
import dev.hnatiuk.carscanner.presentation.common.ActionLiveData
import dev.hnatiuk.core.presentation.base.viewmodel.BaseViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.Event
import javax.inject.Inject

internal sealed class WelcomeEvent : Event

@HiltViewModel
internal class WelcomeViewModel @Inject constructor() : BaseViewModel<WelcomeEvent>() {

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