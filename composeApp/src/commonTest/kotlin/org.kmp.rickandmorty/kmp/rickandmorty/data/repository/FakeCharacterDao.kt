package org.kmp.rickandmorty.kmp.rickandmorty.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kmp.rickandmorty.data.database.CharacterDao
import org.kmp.rickandmorty.data.model.Character

class FakeCharacterDao : CharacterDao {
    private val characterList = mutableListOf<Character>()
    private val charactersFlow = MutableStateFlow<List<Character>>(emptyList())

    override suspend fun upsert(character: Character) {
        val index = characterList.indexOfFirst { it.id == character.id }
        if (index != -1) {
            characterList[index] = character
        } else {
            characterList.add(character)
        }
        emitCharacters()
    }

    override suspend fun delete(character: Character) {
        characterList.removeAll { it.id == character.id }
        emitCharacters()
    }

    override suspend fun deleteAll() {
        characterList.clear()
        emitCharacters()
    }

    override fun getCharacters(): Flow<List<Character>> = charactersFlow.asStateFlow()

    override suspend fun getCharacter(id: String): Character? {
        return characterList.find { it.id == id }
    }

    private fun emitCharacters() {
        charactersFlow.value = characterList.toList()
    }
}
