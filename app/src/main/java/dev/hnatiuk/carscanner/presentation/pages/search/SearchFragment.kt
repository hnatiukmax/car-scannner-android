package dev.hnatiuk.carscanner.presentation.pages.search

import android.os.Handler
import androidx.activity.result.launch
import androidx.core.os.postDelayed
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.hnatiuk.carscanner.databinding.FragmentSearchBinding
import dev.hnatiuk.carscanner.presentation.pages.carinfo.CarInfoActivity
import dev.hnatiuk.carscanner.presentation.pages.numberscanner.NumberScannerActivity
import dev.hnatiuk.core.presentation.base.Inflate
import dev.hnatiuk.core.presentation.base.view.BaseFragment
import dev.hnatiuk.core.presentation.extensions.toast

@AndroidEntryPoint
internal class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchFragmentViewModel, HomeEvent>() {

    override val viewModel: SearchFragmentViewModel by viewModels()

    override val inflate: Inflate<FragmentSearchBinding>
        get() = FragmentSearchBinding::inflate

    private val getNumber = registerForActivityResult(NumberScannerActivity.GetNumber()) {
        toast(it ?: "empty")
        Handler().postDelayed(1000L) {
            startActivity(CarInfoActivity.getIntent(requireContext(), it ?: ""))
        }
    }

    override fun FragmentSearchBinding.initUI() {
        viewModel = this@SearchFragment.viewModel
        scanningSearch.onSearchCardClickListener = {
            getNumber.launch()
        }
    }
}