package org.kmp.rickandmorty.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.StringResource
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.blue_mode
import rickandmorty.composeapp.generated.resources.dark_mode
import rickandmorty.composeapp.generated.resources.green_mode
import rickandmorty.composeapp.generated.resources.light_mode
import rickandmorty.composeapp.generated.resources.red_mode
import rickandmorty.composeapp.generated.resources.system_default


const val BASE_URL = "https://rickandmortyapi.com/api/"
const val DB_Name = "myCharacterDB"
const val dataStoreFileName = "setting.preferences_pb"
val categoryList = arrayListOf("alive", "dead", "unknown")
enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode),
    BLUE_MODE(Res.string.blue_mode),
    GREEN_MODE(Res.string.green_mode),
    RED_MODE(Res.string.red_mode)
}

enum class Type {
    Mobile, Desktop
}
data class Size(
    val width: Dp,
    val height : Dp
)

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )

val FadeOut = fadeOut(animationSpec = tween(90))