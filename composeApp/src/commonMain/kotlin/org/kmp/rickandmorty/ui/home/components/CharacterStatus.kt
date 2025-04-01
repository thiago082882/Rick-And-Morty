package org.kmp.rickandmorty.ui.home.components


import androidx.compose.ui.graphics.Color

sealed class CharacterStatus(val displayName: String, val color: Color) {
    object Alive : CharacterStatus("Alive", Color.Green)
    object Dead : CharacterStatus("Dead", Color.Red)
    object Unknown : CharacterStatus("Unknown", Color.Gray)

    companion object {
        fun fromString(status: String?): CharacterStatus {
            return when (status?.lowercase()) {
                "alive" -> Alive
                "dead" -> Dead
                else -> Unknown
            }
        }
    }
}
