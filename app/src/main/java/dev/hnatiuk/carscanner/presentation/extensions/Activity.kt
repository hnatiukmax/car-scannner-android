package dev.hnatiuk.carscanner.presentation.extensions

import android.content.Context
import android.os.Parcelable
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import dev.hnatiuk.carscanner.domain.core.StringResource
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.presentation.enum.AnimationType
import java.io.Serializable
import java.lang.IllegalArgumentException

internal fun FragmentActivity.hideSoftKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

internal fun FragmentTransaction.setTransition(animationType: AnimationType) {
    val (animIn, animOut) = when (animationType) {
        AnimationType.SLIDE_LEFT -> R.anim.slide_left_enter to R.anim.slide_left_exit
        AnimationType.SLIDE_RIGHT -> R.anim.slide_in_left to R.anim.slide_out_right
    }

    setCustomAnimations(animIn, animOut)
}

internal fun AppCompatActivity.setFullScreen() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

internal fun AppCompatActivity.makeToolbarAsActionBar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(false)
}

internal fun AppCompatActivity.setToolbarTitle(title: String) {
    supportActionBar?.title = title
}

internal fun AppCompatActivity.setToolbarTitle(title: StringResource?) {
    supportActionBar?.title = getString(title?.messageResId ?: return)
}

internal fun AppCompatActivity.enableBackButton(@DrawableRes icon: Int = R.drawable.ic_back_black) = supportActionBar?.let {
    it.setDisplayHomeAsUpEnabled(true)
    it.setDisplayShowHomeEnabled(true)
    it.setHomeAsUpIndicator(icon)
}

internal fun FragmentActivity.onBackPressedHandler(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) {
        onBackPressed()
        return true
    }
    return false
}

internal fun FragmentActivity.intExtraOrException(extraTag: String) : Int {
    return if (intent.hasExtra(extraTag)) {
        intent.getIntExtra(extraTag, 0)
    } else {
        throw IllegalArgumentException("Some int extra arg is missed.")
    }
}

internal fun FragmentActivity.intExtraOrNull(extraTag: String) : Int? {
    return intent.intExtraOrNull(extraTag)
}

internal fun FragmentActivity.stringExtraOrException(extraTag: String) : String {
    return takeIf { intent.hasExtra(extraTag) }?.let {
        intent.getStringExtra(extraTag)
    } ?: throw IllegalArgumentException("Some string extra arg is missed.")
}

internal fun FragmentActivity.stringExtraOrNull(extraTag: String) : String? {
    return intent.getStringExtra(extraTag)
}

internal fun FragmentActivity.booleanExtraOrException(extraTag: String) : Boolean {
    return if (intent.hasExtra(extraTag)) {
        intent.getBooleanExtra(extraTag, false)
    } else {
        throw IllegalArgumentException("Some boolean extra arg is missed.")
    }
}

internal fun FragmentActivity.booleanExtraOrNull(extraTag: String) : Boolean? {
    return if (intent.hasExtra(extraTag)) intent.getBooleanExtra(extraTag, false) else null
}

internal fun <T : Parcelable> FragmentActivity.parcelableExtraOrException(extraTag: String): T {
    return takeIf { intent.hasExtra(extraTag) }?.let {
        intent.getParcelableExtra<T>(extraTag)
    } ?: throw IllegalArgumentException("Some parcelable extra arg is missed.")
}

internal fun <T : Parcelable> FragmentActivity.parcelableExtraOrNull(extraTag: String): T? {
    return  if (intent.hasExtra(extraTag)) intent.getParcelableExtra<T>(extraTag) else null
}

@Suppress("UNCHECKED_CAST")
internal fun <T : Serializable> FragmentActivity.serializableExtraOrException(extraTag: String): T {
    return takeIf { intent.hasExtra(extraTag) }?.let {
        intent.getSerializableExtra(extraTag) as T
    } ?: throw IllegalArgumentException("Some parcelable extra arg is missed.")
}