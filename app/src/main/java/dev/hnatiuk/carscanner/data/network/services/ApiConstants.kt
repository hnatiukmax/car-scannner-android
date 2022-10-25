package dev.hnatiuk.carscanner.data.network.services

import dev.hnatiuk.carscanner.BuildConfig

private const val API_VERSION = BuildConfig.API_VERSION

internal const val API = "/api/$API_VERSION"
internal const val PUB = "/pub/$API_VERSION"