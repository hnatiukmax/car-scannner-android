package dev.hnatiuk.carscanner.presentation.pages.authentication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.hnatiuk.carscanner.domain.extensions.doOnError
import dev.hnatiuk.carscanner.domain.extensions.doOnSuccess
import dev.hnatiuk.carscanner.domain.repository.AuthRepository
import dev.hnatiuk.carscanner.domain.validators.isEmailValid
import dev.hnatiuk.carscanner.domain.validators.isPasswordValid
import dev.hnatiuk.carscanner.domain.validators.isUsernameValid
import dev.hnatiuk.carscanner.presentation.common.ActionLiveData
import dev.hnatiuk.carscanner.presentation.common.not
import dev.hnatiuk.carscanner.presentation.common.valueOrEmpty
import dev.hnatiuk.carscanner.presentation.common.withProgress
import dev.hnatiuk.carscanner.presentation.pages.base.BaseViewModel
import kotlinx.coroutines.launch

internal class RegisterFragmentViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val onRegister = ActionLiveData<Unit>()

    val userName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val userNameHasError = MutableLiveData<Boolean>()
    val emailHasError = MutableLiveData<Boolean>()
    val passwordHasError = MutableLiveData<Boolean>()

    fun onRegisterClick() {
        if (!isAllFieldsValid()) return

        onCloseKeyboard.call()
        viewModelScope.launch {
            isProgressVisible.withProgress {
                authRepository.register(userName.valueOrEmpty, email.valueOrEmpty, password.valueOrEmpty)
                    .doOnSuccess { onShowMessage.value = it.name }
                    .doOnError { onShowError.value = it }
            }
        }
    }

    private fun isAllFieldsValid(): Boolean {
        userNameHasError.value = !userName.value.isUsernameValid
        emailHasError.value = !email.value.isEmailValid
        passwordHasError.value = !password.value.isPasswordValid

        return !userNameHasError && !emailHasError && !passwordHasError
    }
}