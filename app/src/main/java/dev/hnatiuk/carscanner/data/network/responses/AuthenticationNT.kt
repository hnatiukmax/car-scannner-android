package dev.hnatiuk.carscanner.data.network.responses

import dev.hnatiuk.carscanner.domain.entity.AuthResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class AuthenticationNT(
    @Json(name = "name")
    val name: String,
    @Json(name = "token")
    val token: String
)

internal val AuthenticationNT.asDomainEntity get() = AuthResult(name, token)