package dev.hnatiuk.carscanner.presentation.pages.search

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.BaseViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.Event
import javax.inject.Inject

sealed interface HomeEvent : Event

@HiltViewModel
internal class SearchFragmentViewModel @Inject constructor() : BaseViewModel<HomeEvent>()