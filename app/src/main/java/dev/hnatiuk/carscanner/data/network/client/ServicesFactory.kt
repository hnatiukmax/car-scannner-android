package dev.hnatiuk.carscanner.data.network.client

import dev.hnatiuk.carscanner.data.network.services.AuthenticationApiService
import dev.hnatiuk.carscanner.data.network.services.CarInfoService

internal val RestClient.authenticationService
    get() = retrofit.create(AuthenticationApiService::class.java)

internal val RestClient.carInfoService
    get() = retrofit.create(CarInfoService::class.java)