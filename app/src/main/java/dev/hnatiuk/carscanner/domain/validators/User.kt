package dev.hnatiuk.carscanner.domain.validators

import android.util.Patterns

internal val String?.isUsernameValid: Boolean
    get() = !isNullOrBlank()

internal val String?.isEmailValid: Boolean
    get() = this != null && Patterns.EMAIL_ADDRESS.matcher(this).matches()

internal val String?.isPasswordValid: Boolean
    get() = this != null && length > 5