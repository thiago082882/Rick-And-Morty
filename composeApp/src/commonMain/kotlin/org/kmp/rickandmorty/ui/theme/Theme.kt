package org.kmp.rickandmorty.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.kmp.rickandmorty.utils.Theme
import theme.*

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0)
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F)
)



private val BlueDarkColorScheme = darkColorScheme(
    primary = Color(0xff145ac8),
    secondary = Color(0xFF1976D2),
    tertiary = Color(0xFF42A5F5),
    background = Color(0xFF0A192F),
    surface = Color(0xff24406c),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0)
)




private val GreenDarkColorScheme = darkColorScheme(
    primary = Color(0xFF1B5E20),
    secondary = Color(0xFF388E3C),
    tertiary = Color(0xFF4CAF50),
    background = Color(0xFF102A13),
    surface = Color(0xFF1B3A20),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0)
)

private val RedDarkColorScheme = darkColorScheme(
    primary = Color(0xFFB71C1C),
    secondary = Color(0xFFD32F2F),
    tertiary = Color(0xFFE53935),
    background = Color(0xFF2D0B0B),
    surface = Color(0xFF4A1414),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0)
)

@Composable
fun RickAndMortyAppTheme(
    appTheme: String?,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        Theme.LIGHT_MODE.name -> LightColorScheme
        Theme.DARK_MODE.name -> DarkColorScheme
        Theme.BLUE_MODE.name -> BlueDarkColorScheme
        Theme.GREEN_MODE.name -> GreenDarkColorScheme
        Theme.RED_MODE.name ->  RedDarkColorScheme
        else -> if (!darkTheme) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
