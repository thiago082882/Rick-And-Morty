package org.kmp.rickandmorty.ui.character_detail.utils

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.navigation.compose.rememberNavController
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.ui.character_detail.CharacterDetailScreen


@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.setCharacterDetailScreen(character: Character) {
    setContent {
        CharacterDetailScreen(
            navController = rememberNavController(),
            currentCharacter = character
        )
    }
}
