package dev.hnatiuk.carscanner.presentation.view

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.presentation.extensions.ImageViewSrc
import dev.hnatiuk.carscanner.presentation.extensions.TextViewValue
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding

class CarInfoParamView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val mIcon = ImageView(context)
    private val mParam = TextView(context)
    private val mValue = TextView(context)

    var icon by ImageViewSrc(mIcon)
    var param by TextViewValue(mParam)
    var value by TextViewValue(mValue)

    init {
        orientation = HORIZONTAL
        addView(mIcon, LayoutParams(dip(24), dip(24)))
        addView(mParam, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
        addView(mValue, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))

        setLayoutConstraint()
        applyStyle(attrs)
        applyAttrs(attrs)

        if (isInEditMode) {
            mIcon.setImageResource(R.drawable.ic_typing)
            param = "Fuel"
            value = "Diesel"
        }
    }

    private fun applyAttrs(attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CarInfoParamView, defStyleAttr, defStyleRes)
        try {
            icon = a.getResourceId(R.styleable.CarInfoParamView_cip_param_icon, 0)
            param = a.getString(R.styleable.CarInfoParamView_cip_param) ?: ""
            value = a.getString(R.styleable.CarInfoParamView_cip_value) ?: ""
        } finally {
            a.recycle()
        }
    }

    private fun setLayoutConstraint() {
        children.forEach { it.id = View.generateViewId() }

        mIcon.updateLayoutParams<LayoutParams> {
            leftMargin = dip(16)
        }

        mParam.updateLayoutParams<LayoutParams> {
            leftMargin = dip(8)
        }

        mValue.updateLayoutParams<LayoutParams> {
            leftMargin = dip(16)
        }
    }

    private fun applyStyle(attrs: AttributeSet?) {
        gravity = Gravity.CENTER_VERTICAL
        backgroundResource = R.drawable.background_input_edit_text
        elevation = dip(4).toFloat()

        mParam.typeface = Typeface.DEFAULT_BOLD
        mValue.apply {
            padding = dip(10)
            paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(dip(48), MeasureSpec.EXACTLY))
    }
}