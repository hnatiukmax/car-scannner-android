package dev.hnatiuk.core.presentation.base.compose;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

abstract class BaseComposeActivity : ComponentActivity() {

    @Composable
    protected abstract fun Compose()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose()
        }
    }
}