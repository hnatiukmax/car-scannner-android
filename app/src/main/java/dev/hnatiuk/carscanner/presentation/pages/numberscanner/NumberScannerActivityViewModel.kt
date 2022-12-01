package dev.hnatiuk.carscanner.presentation.pages.numberscanner

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.BaseViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.Event
import javax.inject.Inject

sealed interface NumberScannerEvent : Event

@HiltViewModel
internal class NumberScannerActivityViewModel @Inject constructor() :
    BaseViewModel<NumberScannerEvent>()