package dev.hnatiuk.carscanner.data.network.client

import dev.hnatiuk.carscanner.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import java.util.concurrent.TimeUnit

internal class OkHttpClientFactory {

    companion object {
        private const val DEFAULT_CONNECT_TIMEOUT = 20L
        private const val DEFAULT_READ_TIMEOUT = 20L
        private const val DEFAULT_WRITE_TIMEOUT = 20L
    }

    internal fun buildOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptors(interceptors)
            .addInterceptor(getLoggingInterceptor())
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) BODY else NONE
    }

    private fun OkHttpClient.Builder.addInterceptors(interceptors: Array<out Interceptor>) = apply {
        interceptors.forEach {
            addInterceptor(it)
        }
    }
}