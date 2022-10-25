package dev.hnatiuk.carscanner.presentation.pages.authentication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.hnatiuk.carscanner.domain.extensions.doOnError
import dev.hnatiuk.carscanner.domain.extensions.doOnSuccess
import dev.hnatiuk.carscanner.domain.repository.AuthRepository
import dev.hnatiuk.carscanner.presentation.common.ActionLiveData
import dev.hnatiuk.carscanner.presentation.common.not
import dev.hnatiuk.carscanner.presentation.common.valueOrEmpty
import dev.hnatiuk.carscanner.presentation.common.withProgress
import dev.hnatiuk.carscanner.presentation.pages.base.BaseViewModel
import kotlinx.coroutines.launch

internal class LoginFragmentViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val onLogin = ActionLiveData<Unit>()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val emailHasError = MutableLiveData<Boolean>()
    val passwordHasError = MutableLiveData<Boolean>()

    fun onLogInClick() {
        if (!isAllFieldsValid()) return

        onCloseKeyboard.call()
        viewModelScope.launch {
            isProgressVisible.withProgress {
                authRepository.login(email.valueOrEmpty, password.valueOrEmpty)
                    .doOnSuccess { onShowMessage.value = it.name }
                    .doOnError { onShowError.value = it }
            }
        }
    }

    private fun isAllFieldsValid() : Boolean {
        emailHasError.value = email.value.isNullOrEmpty()
        passwordHasError.value = password.value.isNullOrEmpty()

        return !emailHasError && !passwordHasError
    }
}