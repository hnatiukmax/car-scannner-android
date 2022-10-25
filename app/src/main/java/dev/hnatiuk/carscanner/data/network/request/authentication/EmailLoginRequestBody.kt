package dev.hnatiuk.carscanner.data.network.request.authentication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EmailLoginRequestBody(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)