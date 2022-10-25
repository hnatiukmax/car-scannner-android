package dev.hnatiuk.carscanner.data.network

import dev.hnatiuk.carscanner.domain.core.AppException
import dev.hnatiuk.carscanner.domain.core.Result
import dev.hnatiuk.carscanner.presentation.common.getError

internal suspend fun <T> safeApiCall(call: suspend () -> T): Result<T, AppException> {
    return try {
        Result.Success(call.invoke())
    } catch (exception: Exception) {
        Result.Error(getError(exception))
    }
}