package dev.hnatiuk.carscanner.domain.core

internal sealed class Result<out T, out E> {

    internal data class Success<T>(val value: T) : Result<T, Nothing>()

    internal data class Error<E>(val error: E) : Result<Nothing, E>()
}