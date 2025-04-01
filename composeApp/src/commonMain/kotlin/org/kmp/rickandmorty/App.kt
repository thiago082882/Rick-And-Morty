package org.kmp.rickandmorty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.rickandmorty.ui.MainScreen
import org.kmp.rickandmorty.ui.setting.SettingViewModel
import org.kmp.rickandmorty.ui.theme.RickAndMortyAppTheme
import org.koin.compose.KoinContext


@Composable
@Preview
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val currentTheme by settingViewModel.currentTheme.collectAsState()
        RickAndMortyAppTheme(currentTheme) {
            MainScreen(settingViewModel = settingViewModel)
        }
    }
}