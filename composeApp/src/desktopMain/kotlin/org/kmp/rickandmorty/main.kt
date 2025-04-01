package org.kmp.rickandmorty

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.kmp.rickandmorty.di.initKoin
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.logo
import java.awt.Dimension

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication, state = WindowState(
            position = WindowPosition(Alignment.Center)
        ),   title = "RickAndMorty",
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logo)
    ) {
        window.minimumSize = Dimension( 640, 480)
        App()
    }
}