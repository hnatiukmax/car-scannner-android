package dev.hnatiuk.carscanner.presentation.extensions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.doOnEnd

internal fun View.visibleWithAnimation(
    visible: Boolean,
    duration: Long,
    onAnimationEnd: (() -> Unit)? = null
): Animator {
    val alphaRange = if (visible) 0f..1f else 1f..0f
    val (alphaValue, visibilityValue) = if (visible) 0f to View.VISIBLE else 1f to View.GONE

    alpha = alphaValue
    visibility = visibilityValue

    return alphaWithAnimation(alphaRange, duration) {
        if (!visible) {
            visibility = View.GONE
        }
        onAnimationEnd?.invoke()
    }
}

internal fun View.alphaWithAnimation(
    range: ClosedRange<Float>,
    duration: Long,
    onAnimationEnd: (() -> Unit)? = null
) = ObjectAnimator.ofFloat(this, "alpha", range.start, range.endInclusive).apply {
    this.duration = duration
    doOnEnd { onAnimationEnd?.invoke() }
}