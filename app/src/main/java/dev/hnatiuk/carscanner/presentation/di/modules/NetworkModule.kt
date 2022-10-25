package dev.hnatiuk.carscanner.presentation.di.modules

import dev.hnatiuk.carscanner.data.network.client.OkHttpClientFactory
import dev.hnatiuk.carscanner.data.network.client.RestClient
import dev.hnatiuk.carscanner.data.network.client.authenticationService
import dev.hnatiuk.carscanner.data.network.client.carInfoService
import dev.hnatiuk.carscanner.data.network.converters.ConverterFactoryProvider
import dev.hnatiuk.carscanner.data.network.converters.MoshiConverterFactoryProvider
import dev.hnatiuk.carscanner.data.network.services.AuthenticationApiService
import dev.hnatiuk.carscanner.data.network.services.CarInfoService
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

internal val networkModule = module {
    single<ConverterFactoryProvider> { MoshiConverterFactoryProvider() }
    single { OkHttpClientFactory() }
    single { buildRestClient() }
    includeServiceDependencies()
}

/**
 * Api service dependencies.
 */
private fun Module.includeServiceDependencies() {
    single<AuthenticationApiService> { get<RestClient>().authenticationService }
    single<CarInfoService> { get<RestClient>().carInfoService }
}

private fun Scope.buildRestClient() = RestClient(
    baseUrl = get(named(AppConstant.BASE_URL)),
    okHttpClient = get<OkHttpClientFactory>().buildOkHttpClient(),
    converterFactoryProvider = get()
)