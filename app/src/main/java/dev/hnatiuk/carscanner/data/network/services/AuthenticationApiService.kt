package dev.hnatiuk.carscanner.data.network.services

import dev.hnatiuk.carscanner.data.network.request.authentication.EmailLoginRequestBody
import dev.hnatiuk.carscanner.data.network.request.authentication.EmailRegisterRequestBody
import dev.hnatiuk.carscanner.data.network.request.authentication.SocialAuthenticationRequestBody
import dev.hnatiuk.carscanner.data.network.responses.AuthenticationNT
import dev.hnatiuk.carscanner.data.network.services.PUB
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthenticationApiService {

    @POST("$PUB/auth/register")
    suspend fun register(@Body body: EmailRegisterRequestBody): AuthenticationNT

    @POST("$PUB/auth/login")
    suspend fun login(@Body body: EmailLoginRequestBody): AuthenticationNT

    @POST("$PUB/auth/social-register")
    suspend fun socialRegister(@Body body: SocialAuthenticationRequestBody): AuthenticationNT

    @POST("$PUB/auth/social-login")
    suspend fun socialLogin(@Body body: SocialAuthenticationRequestBody): AuthenticationNT
}