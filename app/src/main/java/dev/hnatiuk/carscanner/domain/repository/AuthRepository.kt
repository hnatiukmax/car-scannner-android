package dev.hnatiuk.carscanner.domain.repository

import dev.hnatiuk.carscanner.data.network.request.authentication.EmailLoginRequestBody
import dev.hnatiuk.carscanner.data.network.request.authentication.EmailRegisterRequestBody
import dev.hnatiuk.carscanner.data.network.responses.asDomainEntity
import dev.hnatiuk.carscanner.data.network.safeApiCall
import dev.hnatiuk.carscanner.data.network.services.AuthenticationApiService
import dev.hnatiuk.carscanner.domain.core.AppException
import dev.hnatiuk.carscanner.domain.core.Result
import dev.hnatiuk.carscanner.domain.entity.AuthResult
import dev.hnatiuk.carscanner.domain.extensions.mapOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AuthRepository(
    private val authApiService: AuthenticationApiService
) {

    suspend fun login(email: String, password: String): Result<AuthResult, AppException> = withContext(Dispatchers.IO) {
        safeApiCall { authApiService.login(EmailLoginRequestBody(email, password)) }
            .mapOnSuccess { it.asDomainEntity }
    }

    suspend fun register(username: String, email: String, password: String) = withContext(Dispatchers.IO) {
        safeApiCall { authApiService.register(EmailRegisterRequestBody(username, email, password)) }
            .mapOnSuccess { it.asDomainEntity }
    }
}