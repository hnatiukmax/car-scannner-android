package dev.hnatiuk.carscanner.data.network.services

import dev.hnatiuk.carscanner.data.network.responses.CarInfoNT
import dev.hnatiuk.carscanner.data.network.services.API
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CarInfoService {

    @GET("$API/car-info/{number}")
    suspend fun getCarInfo(@Path("number") number: String): CarInfoNT
}