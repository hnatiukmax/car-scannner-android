package dev.hnatiuk.carscanner.presentation.pages.search

import android.os.Handler
import androidx.activity.result.launch
import androidx.core.os.postDelayed
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.FragmentSearchBinding
import dev.hnatiuk.carscanner.presentation.pages.base.BaseFragment
import dev.hnatiuk.carscanner.presentation.pages.base.Depends
import dev.hnatiuk.carscanner.presentation.pages.carinfo.CarInfoActivity
import dev.hnatiuk.carscanner.presentation.pages.numberscanner.NumberScannerActivity
import dev.hnatiuk.core.presentation.extensions.toast

@Depends(R.layout.fragment_search, SearchFragmentViewModel::class)
internal class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>() {

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