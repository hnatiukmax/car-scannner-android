package dev.hnatiuk.carscanner.presentation.pages.carinfo

import android.content.Context
import android.content.Intent
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.ActivityCarInfoBinding
import dev.hnatiuk.carscanner.presentation.extensions.stringExtraOrException
import dev.hnatiuk.carscanner.presentation.pages.base.BaseToolbarActivity
import dev.hnatiuk.carscanner.presentation.pages.base.Depends
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Depends(R.layout.activity_car_info, CarInfoActivityViewModel::class)
internal class CarInfoActivity :
    BaseToolbarActivity<ActivityCarInfoBinding, CarInfoActivityViewModel>() {

    val number by lazy { stringExtraOrException(ARG_NUMBER) }

    override val viewModel by viewModel<CarInfoActivityViewModel>()

    companion object {
        private const val ARG_NUMBER = "ARG_NUMBER"

        fun getIntent(context: Context, number: String) =
            Intent(context, CarInfoActivity::class.java).apply {
                putExtra(ARG_NUMBER, number)
            }
    }

    override fun ActivityCarInfoBinding.initUI() {
        viewModel = this@CarInfoActivity.viewModel
    }
}