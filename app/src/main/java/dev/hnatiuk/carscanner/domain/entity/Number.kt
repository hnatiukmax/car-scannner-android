package dev.hnatiuk.carscanner.domain.entity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log

data class Number(
    val region: String,
    val number: String,
    val series: String
)

val Number.asString get() = "$region$number$series"

val String.asNumber: Number? get() {
    if (!isCorrectAsNumber()) return null
    val region = substring(0..1)
    val number = substring(2..5)
    val series = substring(5..6)

    return Number(region, number, series)
}

fun String.isCorrectAsNumber() = with(replace("\\s".toRegex(), "")) {
    Log.i("textRecognition", this)
    if (length != 8) return false

    val region = substring(0..1)
    val number = substring(2..5)
    val series = substring(6..7)

    val isRegionCorrect = region.matches(regex = Regex("^[A-W]{2}"))
    val isNumberCorrect = number.matches(regex = Regex("^[0-9]{4}"))
    val isSeriesCorrect = series.matches(regex = Regex("^[A-W]{2}"))

    isRegionCorrect && isNumberCorrect && isSeriesCorrect
}

class SampleActivity : Activity() {

    companion object {
        fun getInstance(context: Context) = Intent(context, SampleActivity::class.java)
    }
}