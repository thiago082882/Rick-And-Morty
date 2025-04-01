package org.kmp.rickandmorty.utils


import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import org.kmp.rickandmorty.data.database.CharactersDatabase


expect fun shareLink(url: String)

expect fun randomUUIDStr():String

expect fun getType():Type

@Composable
expect fun getScreenSize():Size

expect fun getDatabaseBuilder() : RoomDatabase.Builder<CharactersDatabase>