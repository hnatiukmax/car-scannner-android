package dev.hnatiuk.carscanner.data.network.request.authentication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class AuthenticationProvider {
    @Json(name = "facebook")
    FACEBOOK,
    @Json(name = "google")
    GOOGLE,
    @Json(name = "email")
    EMAIL
}