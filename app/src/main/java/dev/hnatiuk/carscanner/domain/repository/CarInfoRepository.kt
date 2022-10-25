package dev.hnatiuk.carscanner.domain.repository

import dev.hnatiuk.carscanner.data.network.safeApiCall
import dev.hnatiuk.carscanner.data.network.services.CarInfoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CarInfoRepository(private val carInfoService: CarInfoService) {

    suspend fun getCarInfoByNumber(number: String) = withContext(Dispatchers.IO) {
        safeApiCall { carInfoService.getCarInfo(number) }
    }
}