package dev.hnatiuk.carscanner.presentation.di.modules

import dev.hnatiuk.carscanner.domain.repository.AuthRepository
import dev.hnatiuk.carscanner.domain.repository.CarInfoRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single { AuthRepository(get()) }
    single { CarInfoRepository(get()) }
}