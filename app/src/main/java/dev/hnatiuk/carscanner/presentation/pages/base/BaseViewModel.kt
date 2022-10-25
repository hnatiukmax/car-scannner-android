package dev.hnatiuk.carscanner.presentation.pages.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.hnatiuk.carscanner.domain.core.AppException
import dev.hnatiuk.carscanner.presentation.common.ActionLiveData

internal open class BaseViewModel : ViewModel() {

    /**
     * Base live variables, that are available for observing in [BaseActivity] or [BaseFragment].
     */
    val isProgressVisible = MutableLiveData<Boolean>()
    val onShowError = MutableLiveData<AppException>()
    val onShowMessage = MutableLiveData<Any>()
    val onCloseKeyboard = ActionLiveData<Unit>()
}