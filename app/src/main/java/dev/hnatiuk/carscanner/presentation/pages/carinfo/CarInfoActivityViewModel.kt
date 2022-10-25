package dev.hnatiuk.carscanner.presentation.pages.carinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.hnatiuk.carscanner.data.network.responses.CarInfoNT
import dev.hnatiuk.carscanner.domain.extensions.doOnError
import dev.hnatiuk.carscanner.domain.extensions.doOnSuccess
import dev.hnatiuk.carscanner.domain.repository.CarInfoRepository
import dev.hnatiuk.carscanner.presentation.common.withProgress
import dev.hnatiuk.carscanner.presentation.pages.base.BaseViewModel
import kotlinx.coroutines.launch

internal class CarInfoActivityViewModel(
    private val number: String,
    private val carInfoRepository: CarInfoRepository
) : BaseViewModel() {

    val carInfo by lazy {
        MutableLiveData<CarInfoNT>().also {
            loadCarInfo()
        }
    }

    private fun loadCarInfo() {
        viewModelScope.launch {
            isProgressVisible.withProgress {
                carInfoRepository.getCarInfoByNumber(number)
                    .doOnSuccess { carInfo.value = it }
                    .doOnError { onShowError.value = it }
            }
        }
    }
}