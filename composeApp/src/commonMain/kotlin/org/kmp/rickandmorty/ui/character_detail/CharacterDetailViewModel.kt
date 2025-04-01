
package org.kmp.rickandmorty.ui.character_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.data.repository.LocalCharacterRepository


class CharacterDetailViewModel(
    private val localNewsRepository: LocalCharacterRepository
) : ViewModel() {

    var isBookmarked by mutableStateOf(false)

    fun isArticleBookmark(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            isBookmarked = localNewsRepository.getCharacter(character.id.toString()) != null
        }
    }

    fun bookmarkArticle(currentArticle: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isBookmarked) {
                localNewsRepository.upsertCharacter(currentArticle)
            } else {
                localNewsRepository.deleteCharacter(currentArticle)
            }
            isBookmarked = !isBookmarked
        }
    }

}