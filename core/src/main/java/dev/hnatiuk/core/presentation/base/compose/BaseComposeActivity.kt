package dev.hnatiuk.core.presentation.base.compose;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import dev.hnatiuk.core.presentation.base.viewmodel.BaseViewModel
import dev.hnatiuk.core.presentation.base.viewmodel.Event

abstract class BaseComposeActivity<VM : BaseViewModel<E>, E : Event> : ComponentActivity() {

    protected abstract val viewModel: VM

    @Composable
    protected abstract fun Compose()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Compose() }
        viewModel.observeViewModel()
        viewModel.event.observe(this, ::handleEvent)
    }

    open fun VM.observeViewModel() {
        //no op
    }

    open fun handleEvent(event: Event) {
        //no op
    }
}