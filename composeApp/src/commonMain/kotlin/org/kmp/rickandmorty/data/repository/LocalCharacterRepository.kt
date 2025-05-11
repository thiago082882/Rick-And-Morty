package org.kmp.rickandmorty.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn
import org.kmp.rickandmorty.data.database.CharacterDao
import org.kmp.rickandmorty.data.model.Character


 open class LocalCharacterRepository(
    private val characterDao: CharacterDao
) {
    suspend fun upsertCharacter(character: Character) {
        characterDao.upsert(character)
    }

    suspend fun deleteCharacter(character: Character) {
        characterDao.delete(character)
    }

    suspend fun deleteAllFavorites() {
        characterDao.deleteAll()
    }

    fun getCharacters() = characterDao.getCharacters().flowOn(Dispatchers.IO)

    suspend fun getCharacter(characterId: String): Character? {
        return characterDao.getCharacter(id = characterId)
    }
}
