package dev.hnatiuk.carscanner.domain.extensions

import dev.hnatiuk.carscanner.domain.core.Result

internal inline fun <I, O, E> Result<I, E>.mapOnSuccess(map: (I) -> O) =
    when (this) {
        is Result.Success -> Result.Success(map.invoke(value))
        is Result.Error -> this
    }

internal inline fun <I, O, E> Result<I, E>.switchMapOnSuccess(map: (I) -> Result<O, E>) =
    when (this) {
        is Result.Success -> map.invoke(value)
        is Result.Error -> this
    }

internal inline fun <T, E> Result<T, E>.doOnSuccess(action: (T) -> Unit) = apply {
    if (this is Result.Success) {
        action.invoke(value)
    }
}

internal inline fun <T, E> Result<T, E>.doOnError(action: (E) -> Unit) = apply {
    if (this is Result.Error) {
        action.invoke(error)
    }
}