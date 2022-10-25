package dev.hnatiuk.carscanner.data.network.client

import dev.hnatiuk.carscanner.data.network.converters.ConverterFactoryProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal class RestClient(
    private val baseUrl: String,
    private val okHttpClient: OkHttpClient,
    private val converterFactoryProvider: ConverterFactoryProvider
) {

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactoryProvider.getConverterFactory())
            .build()
}