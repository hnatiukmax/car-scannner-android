package dev.hnatiuk.carscanner.presentation.common

import dev.hnatiuk.carscanner.domain.core.AppException
import com.squareup.moshi.JsonDataException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

internal fun getError(error: Throwable) = when (error) {
    is ConnectException,
    is UnknownHostException -> AppException.NetworkException(throwable = error)
    is HttpException -> {
        when (error.code()) {
            in 400..499 -> {
                val message = error.getErrorMessage()
                AppException.HttpException.ServerException(
                    message,
                    throwable = error,
                    errorCode = error.code()
                )
            }
            in 500..599 -> AppException.HttpException.ServerException(
                throwable = error,
                errorCode = error.code()
            )
            else -> AppException.HttpException(throwable = error, errorCode = error.code())
        }
    }
    is JsonDataException -> AppException.ParsingAPIResponseException()
    else -> AppException.SomethingBadHappenException(throwable = error)
}


private fun HttpException.getErrorMessage(): String? {
    return response()?.errorBody()?.string()?.let {
        try {
            JSONObject(it).getString("error")
        } catch (e: Throwable) {
            null
        }
    }
}