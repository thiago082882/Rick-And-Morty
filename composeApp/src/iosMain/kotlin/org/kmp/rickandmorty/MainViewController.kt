package org.kmp.rickandmorty

import androidx.compose.ui.window.ComposeUIViewController
import org.kmp.rickandmorty.di.initKoin


fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}