package dev.hnatiuk.carscanner.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

internal sealed class Response<T>

@JsonClass(generateAdapter = true)
internal open class ResultResponse<T>(
    @Json(name = "data")
    val data: T,
    @Json(name = "error")
    val error: String?
) : Response<T>()