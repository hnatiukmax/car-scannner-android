package dev.hnatiuk.carscanner.presentation.pages.base

import androidx.databinding.ViewDataBinding

internal interface BaseView<V : ViewDataBinding, VM : BaseViewModel> {

    private val depends get() = javaClass.getAnnotation(Depends::class.java) ?: throw Depends.AbsentDependsAnnotation()

    val layoutRes get() = depends.layout
    val viewModelClass get() = depends.viewModelClass

    fun VM.observeViewModel() { /* Default implementation */ }

    fun V.initUI() { /* Default implementation */ }
}