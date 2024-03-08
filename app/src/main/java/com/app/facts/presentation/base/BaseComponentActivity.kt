package com.app.facts.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.app.facts.presentation.theme.FactsTheme

abstract class BaseComponentActivity<VM : BaseViewModel<*>> : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FactsTheme {
                ProvideCompose()
            }
        }
    }
    @Composable
    abstract fun ProvideCompose()

    @Composable
    abstract fun ProvideComposeLightPreview()
}