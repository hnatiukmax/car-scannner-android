package dev.hnatiuk.carscanner.presentation.pages.authentication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hnatiuk.carscanner.domain.extensions.doOnError
import dev.hnatiuk.carscanner.domain.extensions.doOnSuccess
import dev.hnatiuk.carscanner.domain.repository.AuthRepository
import dev.hnatiuk.carscanner.presentation.common.ActionLiveData
import dev.hnatiuk.carscanner.presentation.common.not
import dev.hnatiuk.carscanner.presentation.common.valueOrEmpty
import dev.hnatiuk.carscanner.presentation.common.withProgress
import dev.hnatiuk.core.presentation.base.viewmodel.BaseViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.Event
import dev.hnatiuk.core.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface LoginEvent : Event

@HiltViewModel
internal class LoginFragmentViewModel @Inject constructor(
    //private val authRepository: AuthRepository
) : BaseViewModel<LoginEvent>() {

    val openMain = SingleLiveEvent<Unit>()

    val onLogin = ActionLiveData<Unit>()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val emailHasError = MutableLiveData<Boolean>()
    val passwordHasError = MutableLiveData<Boolean>()

    fun onLogInClick() {
        openMain.call()
        if (!isAllFieldsValid()) return

        onCloseKeyboard.call()
        viewModelScope.launch {
            isProgressVisible.withProgress {
//                authRepository.login(email.valueOrEmpty, password.valueOrEmpty)
//                    .doOnSuccess { onShowMessage.value = it.name }
//                    .doOnError { onShowError.value = it }
            }
        }
    }

    private fun isAllFieldsValid() : Boolean {
        emailHasError.value = email.value.isNullOrEmpty()
        passwordHasError.value = password.value.isNullOrEmpty()

        return !emailHasError && !passwordHasError
    }
}