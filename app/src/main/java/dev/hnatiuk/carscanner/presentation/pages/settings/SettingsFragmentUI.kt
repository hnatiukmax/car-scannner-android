package dev.hnatiuk.carscanner.presentation.pages.settings

import android.view.Gravity
import androidx.fragment.app.Fragment
import org.jetbrains.anko.*

class SettingsFragmentUI : AnkoComponent<Fragment> {

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        frameLayout {
            lparams(matchParent, matchParent)
            textView("Settings") {
                gravity = Gravity.CENTER
            }
        }
    }
}