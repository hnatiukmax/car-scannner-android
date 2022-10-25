package dev.hnatiuk.carscanner.presentation.di.modules

import dev.hnatiuk.carscanner.presentation.pages.authentication.AuthViewModel
import dev.hnatiuk.carscanner.presentation.pages.authentication.login.LoginFragmentViewModel
import dev.hnatiuk.carscanner.presentation.pages.authentication.register.RegisterFragmentViewModel
import dev.hnatiuk.carscanner.presentation.pages.bookmarks.BookMarksFragmentViewModel
import dev.hnatiuk.carscanner.presentation.pages.carinfo.CarInfoActivityViewModel
import com.sectumsempra.carinfo.presentation.pages.history.HistoryFragmentViewModel
import com.sectumsempra.carinfo.presentation.pages.numberscanner.NumberScannerActivityViewModel
import dev.hnatiuk.carscanner.presentation.pages.search.SearchFragmentViewModel
import dev.hnatiuk.carscanner.presentation.pages.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { HistoryFragmentViewModel() }
    viewModel { WelcomeViewModel() }
    viewModel { AuthViewModel() }
    viewModel { LoginFragmentViewModel(get()) }
    viewModel { RegisterFragmentViewModel(get()) }
    viewModel { BookMarksFragmentViewModel() }
    viewModel { NumberScannerActivityViewModel() }
    viewModel { SearchFragmentViewModel() }
    viewModel { CarInfoActivityViewModel("number", get()) }
}