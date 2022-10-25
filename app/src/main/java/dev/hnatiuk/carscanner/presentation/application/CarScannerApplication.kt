package dev.hnatiuk.carscanner.presentation.application

import android.app.Application
import dev.hnatiuk.carscanner.presentation.di.provideModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

internal class CarScannerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initServiceLocator()
    }

    private fun initServiceLocator() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@CarScannerApplication)
            modules(*provideModules())
        }
    }
}