package org.kmp.rickandmorty.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import org.kmp.rickandmorty.ui.navigation.graphs.RootNavGraph
import org.kmp.rickandmorty.ui.setting.SettingViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainScreen(settingViewModel: SettingViewModel) {
    val windowSizeClass = calculateWindowSizeClass()
    val isMediumExpandedWWSC = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    val rootNavController = rememberNavController()

    Scaffold { innerPadding: PaddingValues ->
        RootNavGraph(
            rootNavController = rootNavController,
            innerPadding = innerPadding,
            settingViewModel = settingViewModel
        )
    }
}
