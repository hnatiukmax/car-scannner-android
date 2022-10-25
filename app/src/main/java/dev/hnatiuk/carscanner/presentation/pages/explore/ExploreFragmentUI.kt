package dev.hnatiuk.carscanner.presentation.pages.explore

import android.view.Gravity
import androidx.fragment.app.Fragment
import org.jetbrains.anko.*

class ExploreFragmentUI : AnkoComponent<Fragment> {

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        frameLayout {
            lparams(matchParent, matchParent)
            textView("Explore") {
                gravity = Gravity.CENTER
            }
        }
    }
}