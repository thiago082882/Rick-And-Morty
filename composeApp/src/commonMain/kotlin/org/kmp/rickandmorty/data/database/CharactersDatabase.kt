package org.kmp.rickandmorty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.kmp.rickandmorty.data.model.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(CharacterTypeConverters::class)
abstract class CharactersDatabase : RoomDatabase(), DB {
    abstract fun characterDao(): CharacterDao
    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables(): Unit {}
}