package dev.hnatiuk.carscanner.presentation.pages.authentication

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.BaseViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.Event
import javax.inject.Inject

sealed interface AuthEvent : Event

@HiltViewModel
internal class AuthViewModel @Inject constructor() : BaseViewModel<AuthEvent>() {


}