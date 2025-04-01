package org.kmp.rickandmorty.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.kmp.rickandmorty.data.model.Character


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(character: Character)

    @Delete
    suspend fun delete(character: Character)

    @Query("DELETE FROM characterTable")
    suspend fun deleteAll()

    @Query("SELECT * FROM characterTable")
    fun getCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM characterTable WHERE id=:id")
    suspend fun getCharacter(id: String): Character?

}