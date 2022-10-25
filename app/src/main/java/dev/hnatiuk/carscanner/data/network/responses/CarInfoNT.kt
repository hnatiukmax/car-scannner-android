package dev.hnatiuk.carscanner.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarInfoNT(
    @Json(name = "digits")
    val digits: String,
    @Json(name = "vendor")
    val vendor: String,
    @Json(name = "model")
    val model: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "year")
    val year: String,
    @Json(name = "color")
    val color: String,
    @Json(name = "fuel")
    val fuel: String,
    @Json(name = "engine_size")
    val engineSize: String,
    @Json(name = "stolen")
    val isStolen: Boolean,
    @Json(name = "photoUrl")
    val photoUrl: String,
    @Json(name = "operations")
    val operations: List<OperationNT>
) {

    data class OperationNT(
        @Json(name = "isLast")
        val isLast: Boolean,
        @Json(name = "regAt")
        val regAt: String,
        @Json(name = "operation")
        val operation: String,
        @Json(name = "address")
        val address: String
    )
}