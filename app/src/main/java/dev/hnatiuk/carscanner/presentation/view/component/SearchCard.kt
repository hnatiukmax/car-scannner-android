package dev.hnatiuk.carscanner.presentation.view.component

import android.graphics.Typeface
import android.view.ViewManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.presentation.extensions.generateId
import dev.hnatiuk.carscanner.presentation.extensions.imageView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

internal inline fun ViewManager.searchCard(
    @DrawableRes logoRes: Int,
    @DrawableRes iconRes: Int,
    @DrawableRes backgroundIconRes: Int,
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    init: RelativeLayout.() -> Unit = {}
) = relativeLayout {
    init.invoke(this)
    lparams(width = matchParent) {
        margin = dip(16)
    }
    backgroundResource = R.drawable.background_search_card
    elevation = dip(4).toFloat()

    val logo = cardView {
        radius = dip(8).toFloat()
        generateId()
        imageView(logoRes, withId = true) {
            scaleType = ImageView.ScaleType.CENTER_CROP
        }.lparams(width = matchParent)
    }.lparams(width = matchParent)

    val icon = imageView(iconRes, withId = true) {
        backgroundResource = backgroundIconRes
        padding = dip(10)
    }.lparams {
        setMargins(dip(14), dip(14), dip(16), dip(14))
        below(logo)
    }

    textView(text = titleRes) {
        typeface = Typeface.DEFAULT_BOLD
    }.lparams {
        sameTop(icon)
        endOf(icon)
    }

    textView(text = descriptionRes)
        .lparams {
            sameBottom(icon)
            endOf(icon)
        }
}