package dev.hnatiuk.carscanner.presentation.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec.makeMeasureSpec
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.presentation.extensions.ImageViewBackground
import dev.hnatiuk.carscanner.presentation.extensions.ImageViewSrc
import dev.hnatiuk.carscanner.presentation.extensions.TextViewValue
import org.jetbrains.anko.*

class SearchCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val mLogoContainer = CardView(context)
    private val mLogo = ImageView(context)
    private val mIcon = ImageView(context)

    private val mTitle = TextView(context)
    private val mDescription = TextView(context)

    var title by TextViewValue(mTitle)
    var description by TextViewValue(mDescription)

    var logo by ImageViewSrc(mLogo)
    var icon by ImageViewSrc(mIcon)
    var iconBackground by ImageViewBackground(mIcon)

    var onSearchCardClickListener: ((String) -> Unit)? = null

    init {
        addView(mLogoContainer, LayoutParams(MATCH_PARENT, WRAP_CONTENT))
        mLogoContainer.addView(mLogo, LayoutParams(MATCH_PARENT, WRAP_CONTENT))

        addView(mIcon, dip(48), dip(48))
        addView(mTitle, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(mDescription, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))

        setLayoutConstraint()
        applyStyle(attrs)
        applyAttrs(attrs)

        if (isInEditMode) {
            mIcon.apply {
                backgroundResource = R.drawable.background_scanning_icon
                imageResource = R.drawable.ic_camera
            }
            mLogo.setImageResource(R.drawable.logo_scanning)
            mTitle.textResource = R.string.search_card_scanning_title
            mDescription.textResource = R.string.search_card_scanning_description
        }
    }

    private fun applyAttrs(
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.SearchCardView, defStyleAttr, defStyleRes)
        try {
            title = a.getString(R.styleable.SearchCardView_sc_title) ?: ""
            description = a.getString(R.styleable.SearchCardView_sc_description) ?: ""
            logo = a.getResourceId(R.styleable.SearchCardView_sc_logo, 0)
            icon = a.getResourceId(R.styleable.SearchCardView_sc_icon, 0)
            iconBackground = a.getResourceId(R.styleable.SearchCardView_sc_icon_background, 0)
        } finally {
            a.recycle()
        }
    }

    private fun setLayoutConstraint() {
        children.forEach { it.id = View.generateViewId() }

        mLogoContainer.updateLayoutParams<LayoutParams> {
            startToStart = LayoutParams.PARENT_ID
            endToEnd = LayoutParams.PARENT_ID
            topToTop = LayoutParams.PARENT_ID
        }

        mIcon.updateLayoutParams<LayoutParams> {
            startToStart = LayoutParams.PARENT_ID
            topToBottom = mLogoContainer.id
            marginStart = dip(16)
            topMargin = dip(16)
        }

        mTitle.updateLayoutParams<LayoutParams> {
            startToEnd = mIcon.id
            topToTop = mIcon.id
            marginStart = dip(12)
        }

        mDescription.updateLayoutParams<LayoutParams> {
            startToEnd = mIcon.id
            bottomToBottom = mIcon.id
            marginStart = dip(12)
        }
    }

    private fun applyStyle(attrs: AttributeSet?) {
        setOnClickListener {
            onSearchCardClickListener?.invoke(title)
        }
        backgroundResource = R.drawable.background_search_card
        elevation = dip(4).toFloat()
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        bottomPadding = dip(16)

        mLogoContainer.radius = dip(8).toFloat()
        mLogo.scaleType = ImageView.ScaleType.CENTER_CROP
        mIcon.padding = dip(10)
        mTitle.typeface = Typeface.DEFAULT_BOLD
        mDescription.apply {
            setTextAppearance(R.style.AppTheme_Text_Description)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, makeMeasureSpec(minimumHeight, MeasureSpec.UNSPECIFIED))
    }
}