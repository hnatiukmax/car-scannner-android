package dev.hnatiuk.carscanner.presentation.di

import dev.hnatiuk.carscanner.presentation.di.modules.applicationIdentifiersDependencies
import dev.hnatiuk.carscanner.presentation.di.modules.networkModule
import dev.hnatiuk.carscanner.presentation.di.modules.repositoryModule
import dev.hnatiuk.carscanner.presentation.di.modules.viewModelModule

internal fun provideModules() = arrayOf(
    applicationIdentifiersDependencies,
    networkModule,
    repositoryModule,
    viewModelModule
)